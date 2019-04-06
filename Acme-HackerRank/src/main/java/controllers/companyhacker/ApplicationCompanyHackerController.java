
package controllers.companyhacker;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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

	// Request List -----------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		Collection<Application> pendingApplications;
		Collection<Application> submittedApplications;
		Collection<Application> acceptedApplications;
		Collection<Application> rejectedApplications;

		Integer hackerId;

		pendingApplications = this.applicationService.findPendingApplicationsByHacker();
		submittedApplications = this.applicationService.findSubmittedApplicationsByHacker();
		acceptedApplications = this.applicationService.findAcceptedApplicationsByHacker();
		rejectedApplications = this.applicationService.findRejectedApplicationsByHacker();
		hackerId = this.hackerService.findByPrincipal().getId();

		result = new ModelAndView("application/list");
		result.addObject("pendingApplications", pendingApplications);
		result.addObject("submittedApplications", submittedApplications);
		result.addObject("acceptedApplications", acceptedApplications);
		result.addObject("rejectedApplications", rejectedApplications);
		result.addObject("hackerId", hackerId);
		result.addObject("requestURI", "application/company,hacker/list.do");

		return result;
	}
}
