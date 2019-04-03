
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.CustomisationService;
import services.PositionService;
import domain.Actor;
import domain.Administrator;
import domain.Company;
import domain.Position;

@Controller
public class ActorAbstractController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private ActorService			actorService;

	@Autowired
	private CustomisationService	customisationService;

	@Autowired
	private PositionService			positionService;


	// Main methods -----------------------------------------------------------

	// Display --------------------------------------------------------------------

	public ModelAndView display(final Integer actorId) {
		ModelAndView result;
		Actor actor, principal;
		final Collection<Position> positions;

		actor = null;
		principal = null;
		try {
			principal = this.actorService.findPrincipal();
		} catch (final Throwable oops) {

		}
		result = new ModelAndView("actor/display");

		if (actorId == null) {
			actor = this.actorService.findPrincipal();
			result.addObject("isAuthorized", true);
			result.addObject("isActorLogged", true);
			if (actor instanceof Company) {
				positions = this.positionService.findFinalModePositionsByCompany(actor.getId());
				if (positions.isEmpty())
					result.addObject("emptyPositions", true);
				else
					result.addObject("emptyPositions", false);
			}
		} else {
			actor = this.actorService.findOne(actorId);
			if (actor instanceof Administrator && actorId == principal.getId())
				actor = this.actorService.findOneToDisplayEdit(actorId);
			else if (actor instanceof Administrator && actorId != principal.getId())
				throw new IllegalArgumentException();
			if (actor instanceof Company) {
				positions = this.positionService.findFinalModePositionsByCompany(actor.getId());
				if (positions.isEmpty())
					result.addObject("emptyPositions", true);
				else
					result.addObject("emptyPositions", false);
			}

		}

		if (principal != null && actor != null && principal == actor) {
			result.addObject("isActorLogged", true);
			result.addObject("isAuthorized", true);
		}

		result.addObject("actor", actor);

		return result;
	}
	// Ancillary methods ------------------------------------------------------

}
