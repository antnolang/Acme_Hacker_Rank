
package controllers.hacker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AnswerService;
import services.ApplicationService;
import services.HackerService;
import controllers.AbstractController;
import domain.Answer;

@Controller
@RequestMapping(value = "/answer/hacker")
public class AnswerHackerController extends AbstractController {

	// Services------------------------------------

	@Autowired
	private ApplicationService	applicationService;

	@Autowired
	private AnswerService		answerService;

	@Autowired
	private HackerService		hackerService;


	// Constructors -----------------------------------------------------------

	public AnswerHackerController() {
		super();
	}

	// Request create -----------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;

		try {
			final Answer answer;

			answer = this.answerService.create();

			result = this.createEditModelAndView(answer);

		} catch (final Throwable oops) {

			result = new ModelAndView("redirect:../../error.do");
		}
		return result;
	}

	//Edit
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int answerId) {
		ModelAndView result;
		Answer answer;

		try {

			answer = this.answerService.findOne(answerId);
			result = this.createEditModelAndView(answer);

		} catch (final Exception e) {
			result = new ModelAndView("redirect:../../error.do");
		}

		return result;
	}

	//Save
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(final Answer answer, final BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(answer);
		else
			try {
				this.answerService.save(answer);
				result = new ModelAndView("redirect:../application/hacker/list.do");
			}

			catch (final Throwable oops) {
				result = this.createEditModelAndView(answer, "answer.commit.error");
			}

		return result;
	}

	// Arcillary methods --------------------------

	protected ModelAndView createEditModelAndView(final Answer answer) {
		ModelAndView result;

		result = this.createEditModelAndView(answer, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final Answer answer, final String messageCode) {
		ModelAndView result;

		result = new ModelAndView("answer/edit");
		result.addObject("messageCode", messageCode);
		return result;

	}
}
