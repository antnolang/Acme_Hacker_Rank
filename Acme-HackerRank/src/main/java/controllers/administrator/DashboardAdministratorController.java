
package controllers.administrator;

import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.PositionService;
import controllers.AbstractController;
import domain.Position;

@Controller
@RequestMapping("dashboard/administrator")
public class DashboardAdministratorController extends AbstractController {

	// Services ------------------

	@Autowired
	private PositionService	positionService;


	// Constructors --------------
	public DashboardAdministratorController() {
		super();
	}

	// methods --------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(final Locale locale) {
		final ModelAndView result;

		result = new ModelAndView("dashboard/display");

		// LEVEL C -----------------------------------------

		// Req 11.2.1

		// Req 11.2.2

		// Req 11.2.3

		// Req 11.2.4

		// Req 11.2.5
		Double[] dataSalaryOffered;
		dataSalaryOffered = this.positionService.findDataSalaryOffered();
		result.addObject("dataSalaryOffered", dataSalaryOffered);

		// Req 11.2.6
		final List<Position> dataPositionsBestWorstSalary;
		dataPositionsBestWorstSalary = this.positionService.findPositionsBestWorstSalary();
		result.addObject("dataPositionsBestWorstSalary", dataPositionsBestWorstSalary);

		// LEVEL B --------------------------------------

		// Req 18.1.1

		// Req 18.1.2

		// Req 18.1.3

		return result;
	}
}
