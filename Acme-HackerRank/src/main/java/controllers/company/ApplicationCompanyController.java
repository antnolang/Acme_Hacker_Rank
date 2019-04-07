
package controllers.company;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ApplicationService;
import services.HackerService;
import services.PositionService;
import controllers.AbstractController;
import domain.Application;
import domain.Hacker;
import domain.Position;

@Controller
@RequestMapping(value = "/application/company")
public class ApplicationCompanyController extends AbstractController {

	// Services------------------------------------

	@Autowired
	private ApplicationService	applicationService;

	@Autowired
	private PositionService		positionService;

	@Autowired
	private HackerService		hackerService;


	// Constructors -----------------------------------------------------------

	public ApplicationCompanyController() {
		super();
	}

	// Request List -----------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int positionId) {
		final ModelAndView result;
		Collection<Application> submittedApplications;
		Collection<Application> acceptedApplications;
		Collection<Application> rejectedApplications;

		submittedApplications = this.applicationService.findSubmittedApplicationsByPosition(positionId);
		acceptedApplications = this.applicationService.findAcceptedApplicationsByPosition(positionId);
		rejectedApplications = this.applicationService.findRejectedApplicationsByPosition(positionId);

		result = new ModelAndView("application/list");
		result.addObject("submittedApplications", submittedApplications);
		result.addObject("acceptedApplications", acceptedApplications);
		result.addObject("rejectedApplications", rejectedApplications);

		result.addObject("requestURI", "application/company/list.do?positionId=" + positionId);

		return result;
	}

	// Request create -----------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam final int positionId) {
		ModelAndView result;

		try {
			final Position position;
			final Application application;

			position = this.positionService.findOneToDisplay(positionId);
			application = this.applicationService.create(position);

			result = this.createEditModelAndView(application);

		} catch (final Throwable oops) {

			result = new ModelAndView("redirect:../../error.do");
		}
		return result;
	}

	//Edit
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int applicationId) {
		ModelAndView result;
		Application application;

		try {

			application = this.applicationService.findOneToHacker(applicationId);
			result = this.createEditModelAndView(application);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:../../error.do");
		}

		return result;
	}

	//Save
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(final Application application, final BindingResult binding) {
		ModelAndView result;
		Application applicationRec;
		final Hacker principal;

		applicationRec = this.applicationService.reconstruct(application, binding);
		if (binding.hasErrors())
			result = this.createEditModelAndView(application);
		else
			try {

				principal = this.hackerService.findByPrincipal();
				this.applicationService.save(applicationRec);
				result = new ModelAndView("redirect:../list.do?hackerId=" + principal.getId());
			}

			catch (final Throwable oops) {
				result = this.createEditModelAndView(application, "application.commit.error");
			}

		return result;
	}

	// Arcillary methods --------------------------

	protected ModelAndView createEditModelAndView(final Application application) {
		ModelAndView result;

		result = this.createEditModelAndView(application, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Application application, final String messageCode) {
		ModelAndView result;
		int hackerId;

		hackerId = this.hackerService.findByPrincipal().getId();

		result = new ModelAndView("application/edit");
		result.addObject("application", application);
		result.addObject("messageCode", messageCode);
		result.addObject("hackerId", hackerId);

		return result;

	}
}
