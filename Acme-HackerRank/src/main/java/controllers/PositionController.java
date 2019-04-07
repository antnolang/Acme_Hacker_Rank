
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ApplicationService;
import services.CompanyService;
import services.HackerService;
import services.PositionService;
import domain.Company;
import domain.Hacker;
import domain.Position;
import domain.Problem;

@Controller
@RequestMapping(value = "/position")
public class PositionController extends AbstractController {

	// Services------------------------------------

	@Autowired
	private PositionService		positionService;

	@Autowired
	private CompanyService		companyService;

	@Autowired
	private ApplicationService	applicationService;

	@Autowired
	private HackerService		hackerService;


	// Constructor ------------------------------------

	public PositionController() {
		super();
	}

	// List------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int companyId) {
		ModelAndView result;
		Collection<Position> positions;
		Company principal;
		Company owner;

		try {
			result = new ModelAndView("position/list");
			positions = this.positionService.findFinalModePositionsByCompany(companyId);
			owner = this.companyService.findOne(companyId);

			try {
				principal = this.companyService.findByPrincipal();
			} catch (final Exception e1) {
				principal = null;
			}

			if (principal != null && principal.getId() == companyId) {
				result.addObject("principal", principal);
				positions = this.positionService.findPositionByPrincipal();
			}
			result.addObject("principal", principal);
			result.addObject("owner", owner);
			result.addObject("positions", positions);
			result.addObject("requestURI", "position/list.do?companyId=" + companyId);

		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:../error.do");
		}

		return result;
	}

	@RequestMapping(value = "/availableList", method = RequestMethod.GET)
	public ModelAndView availableList() {
		ModelAndView result;
		Collection<Position> positions;
		Company principal;

		try {
			result = new ModelAndView("position/list");

			positions = this.positionService.findAllPositionAvailable();
			try {
				principal = this.companyService.findByPrincipal();
			} catch (final Exception e1) {
				principal = null;
			}
			if (principal != null)
				result.addObject("principal", principal);

			result.addObject("owner", null);
			result.addObject("positions", positions);
			result.addObject("requestURI", "position/availableList.do");

		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:../error.do");
		}

		return result;
	}

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search(@RequestParam(value = "keyword", defaultValue = "") final String keyword) {
		ModelAndView result;
		Collection<Position> positions;

		positions = this.positionService.searchByKeyword(keyword);

		result = new ModelAndView("position/search");
		result.addObject("keyword", keyword);
		result.addObject("positions", positions);
		result.addObject("isSearch", true);
		result.addObject("requestURI", "position/search.do");

		return result;
	}

	// Display------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int positionId) {
		ModelAndView result;
		Position position;
		Company principal;
		Collection<Problem> problemList;
		Boolean isApplied;
		Hacker hackerPrincipal;

		try {
			result = new ModelAndView("position/display");
			position = this.positionService.findOne(positionId);

			try {
				principal = this.companyService.findByPrincipal();
			} catch (final Exception e1) {
				principal = null;
			}

			try {
				hackerPrincipal = this.hackerService.findByPrincipal();
			} catch (final Exception e1) {
				hackerPrincipal = null;
			}

			if (principal != null && principal.equals(position.getCompany())) {
				position = this.positionService.findOne(positionId);
				problemList = position.getProblems();

				result.addObject("principal", principal);
				result.addObject("problemList", problemList);
			} else if (hackerPrincipal != null && hackerPrincipal.getUserAccount().getAuthorities().toString().equals("[HACKER]")) {
				position = this.positionService.findOne(positionId);
				hackerPrincipal = this.hackerService.findByPrincipal();
				isApplied = this.applicationService.isApplied(position, hackerPrincipal);
				result.addObject("isApplied", isApplied);
			}

			else
				position = this.positionService.findOneToDisplay(positionId);

			result.addObject("position", position);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:../error.do");
		}

		return result;
	}
}
