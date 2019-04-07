
package controllers.companyhacker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.ApplicationService;
import services.HackerService;
import services.PositionService;
import controllers.AbstractController;

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

}
