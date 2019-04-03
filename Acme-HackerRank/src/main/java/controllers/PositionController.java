
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CompanyService;
import services.PositionService;
import domain.Company;
import domain.Position;

@Controller
@RequestMapping(value = "/position")
public class PositionController extends AbstractController {

	// Services------------------------------------

	@Autowired
	private PositionService	positionService;

	@Autowired
	private CompanyService	companyService;


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

		try {
			result = new ModelAndView("position/list");

			try {
				principal = this.companyService.findByPrincipal();
			} catch (final Exception e1) {
				principal = null;
			}

			if (principal != null && principal.getId() == companyId)
				result.addObject("principal", principal);

			positions = this.positionService.findFinalModePositionsByCompany(companyId);

			result.addObject("positions", positions);

		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:../error.do");
		}

		return result;
	}

	// Display------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int positionId) {
		ModelAndView result;
		Position position;
		Company principal;

		try {
			result = new ModelAndView("position/display");
			position = this.positionService.findOne(positionId);

			try {
				principal = this.companyService.findByPrincipal();
			} catch (final Exception e1) {
				principal = null;
			}

			if (principal != null && principal.equals(position.getCompany()))
				result.addObject("principal", principal);
			else
				position = this.positionService.findOneToDisplay(positionId);

			position = this.positionService.findOneToDisplay(positionId);

			result.addObject("position", position);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:../error.do");
		}

		return result;
	}
}
