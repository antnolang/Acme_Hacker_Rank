
package controllers.companyhacker;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import security.UserAccount;
import services.ProblemService;
import controllers.AbstractController;
import domain.Position;
import domain.Problem;

@Controller
@RequestMapping(value = "problem/company,hacker")
public class ProblemCompanyHackerController extends AbstractController {

	// Services------------------------------------

	@Autowired
	private ProblemService	problemService;


	// Constructor ------------------------------------

	public ProblemCompanyHackerController() {
		super();
	}

	// Display------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int problemId) {
		ModelAndView result;
		Problem problem;
		UserAccount userPrincipal;
		Collection<Position> positionsList;

		try {

			result = new ModelAndView("problem/display");
			userPrincipal = LoginService.getPrincipal();

			if (userPrincipal.getAuthorities().toString().equals("[COMPANY]")) {
				problem = this.problemService.findOneToPrincipal(problemId);
				positionsList = problem.getPositions();

				result.addObject("problem", problem);
				result.addObject("positionsList", positionsList);
			} else if (userPrincipal.getAuthorities().toString().equals("[HACKER]")) {
				problem = this.problemService.findOneToDisplayHacker(problemId);
				positionsList = problem.getPositions();

				result.addObject("problem", problem);
				result.addObject("positionsList", positionsList);
			}
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:../error.do");
		}

		return result;
	}
}
