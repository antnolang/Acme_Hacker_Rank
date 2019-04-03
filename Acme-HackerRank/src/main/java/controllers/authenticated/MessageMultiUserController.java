
package controllers.authenticated;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AdministratorService;
import services.MessageService;
import controllers.AbstractController;
import domain.Actor;
import domain.Message;

@Controller
@RequestMapping("/message/administrator,company,hacker")
public class MessageMultiUserController extends AbstractController {

	@Autowired
	private MessageService			messageService;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private AdministratorService	administratorService;


	public MessageMultiUserController() {
		super();
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int messageId) {
		ModelAndView result;
		Message message;

		try {
			message = this.messageService.findOneToDisplay(messageId);

			result = new ModelAndView("message/display");
			result.addObject("messageToDisplay", message);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/error.do");
		}

		return result;
	}

	public ModelAndView list() {
		ModelAndView result;
		Collection<Message> sentMessages, receivedMessages;
		Actor principal;

		principal = this.actorService.findPrincipal();

		sentMessages = this.messageService.findSentMessagesOrderByTags(principal.getId());
		receivedMessages = this.messageService.findReceivedMessagesOrderByTags(principal.getId());

		result = new ModelAndView("message/list");
		result.addObject("sentMessages", sentMessages);
		result.addObject("receivedMessages", receivedMessages);

		return result;
	}

	@RequestMapping(value = "/send", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Message message;

		message = this.messageService.create();

		result = this.createEditModelAndView(message);

		return result;

	}

	@RequestMapping(value = "/send", method = RequestMethod.POST, params = "send")
	public ModelAndView send(final Message message, final BindingResult binding) {
		ModelAndView result;
		Message messageRec;

		messageRec = this.messageService.reconstruct(message, binding);
		if (binding.hasErrors())
			result = this.createEditModelAndView(message);
		else
			try {
				this.messageService.send(messageRec);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(messageRec, "message.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int messageId, @RequestParam final int boxId) {
		ModelAndView result;
		Message message;

		message = this.messageService.findOne(messageId);
		try {
			this.messageService.delete(message);

			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/error.do");
		}

		return result;
	}

	// Ancillary methods ----------------------------------
	protected ModelAndView createEditModelAndView(final Message message) {
		ModelAndView result;

		result = this.createEditModelAndView(message, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Message message, final String messageCode) {
		ModelAndView result;
		Collection<Actor> actors;
		Actor principal, system;

		system = this.administratorService.findSystem();
		principal = this.actorService.findPrincipal();

		actors = this.actorService.findAll();
		actors.remove(principal);
		actors.remove(system);

		result = new ModelAndView("message/send");
		result.addObject("message", message);
		result.addObject("actors", actors);
		result.addObject("isBroadcastMessage", false);
		result.addObject("actionURI", "message/administrator,company,hacker/send.do");
		result.addObject("messageCode", messageCode);

		return result;
	}
}
