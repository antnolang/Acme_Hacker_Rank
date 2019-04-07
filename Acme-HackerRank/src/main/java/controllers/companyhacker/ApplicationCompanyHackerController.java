
package controllers.companyhacker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.LoginService;
import services.ApplicationService;
import services.HackerService;
import services.PositionService;
import controllers.AbstractController;
import domain.Application;

@Controller
@RequestMapping(value = "/application/company,hacker")
public class ApplicationCompanyHackerController extends AbstractController {

	// Services------------------------------------

	@Autowired
	private ApplicationService	applicationService;

	@Autowired
	private PositionService		positionService;

	@Autowired
	private HackerService		hackerService;


	// Constructors -----------------------------------------------------------

	public ApplicationCompanyHackerController() {
		super();
	}

	// Application Display -----------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int applicationId) {
		ModelAndView result;
		Application application;
		String rolActor;

		try {
			result = new ModelAndView("application/display");
			if (LoginService.getPrincipal().getAuthorities().toString().equals("[HACKER]")) {
				application = this.applicationService.findOneToHacker(applicationId);
				rolActor = "hacker";
			} else {
				application = this.applicationService.findOneToCompany(applicationId);
				rolActor = "company";
			}
			result.addObject("application", application);
			result.addObject("rolActor", rolActor);
		} catch (final Exception e) {
			result = new ModelAndView("redirect:../../error.do");
		}

		return result;
	}

}
