
package controllers.company;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CompanyService;
import services.PositionService;
import services.ProblemService;
import controllers.AbstractController;
import domain.Problem;

@Controller
@RequestMapping(value = "/problem/company")
public class ProblemCompanyController extends AbstractController {

	// Services------------------------------------

	@Autowired
	private ProblemService	problemService;

	@Autowired
	private PositionService	positionService;

	@Autowired
	private CompanyService	companyService;


	// Constructor ------------------------------------

	public ProblemCompanyController() {
		super();
	}

	// List------------------------------------

	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public ModelAndView listAll() {
		ModelAndView result;
		Collection<Problem> problems;

		try {
			result = new ModelAndView("problem/list");
			problems = this.problemService.findProblemsByPrincipal();

			result.addObject("problems", problems);

		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:../error.do");
		}

		return result;
	}

}
