
package controllers.companyhacker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import security.UserAccount;
import services.CompanyService;
import services.PositionService;
import services.ProblemService;
import controllers.AbstractController;
import domain.Problem;

@Controller
@RequestMapping(value = "problem/company,hacker")
public class ProblemCompanyHackerController extends AbstractController {

	// Services------------------------------------

	@Autowired
	private ProblemService	problemService;

	@Autowired
	private PositionService	positionService;

	@Autowired
	private CompanyService	companyService;


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

		try {

			result = new ModelAndView("problem/display");
			userPrincipal = LoginService.getPrincipal();

			if (userPrincipal.getAuthorities().toString().equals("[COMPANY]")) {
				problem = this.problemService.findOneToPrincipal(problemId);
				result.addObject("problem", problem);
			} else if (userPrincipal.getAuthorities().toString().equals("[HACKER]")) {
				problem = this.problemService.findOneToDisplayHacker(problemId);
				result.addObject("problem", problem);
			}

		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:../error.do");
		}

		return result;
	}
}
