
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AnswerRepository;
import domain.Answer;
import domain.Application;

@Service
@Transactional
public class AnswerService {

	// Managed repository ---------------------------------------------
	@Autowired
	private AnswerRepository	answerRepository;

	// Supporting services -------------------------------------------

	@Autowired
	private HackerService		hackerService;

	@Autowired
	private CompanyService		companyService;

	@Autowired
	private ApplicationService	applicationService;


	//Constructor ----------------------------------------------------
	public AnswerService() {
		super();
	}

	//Simple CRUD methods -------------------------------------------
	public Answer create() {
		Answer result;

		result = new Answer();

		return result;
	}
	public Answer save(final Answer answer) {
		Assert.isTrue(!(answer.getCodeLink().equals(null)));
		Assert.isTrue(!(answer.getText().equals(null)));
		Assert.isTrue(!(answer.getText().equals("")));
		Answer result;

		result = this.answerRepository.save(answer);

		return result;
	}

	public Answer findOne(final int answerId) {
		Answer result;

		result = this.answerRepository.findOne(answerId);

		return result;
	}

	public Answer findOneToHackerDisplay(final int answerId) {
		Application application;
		Answer result;

		application = this.applicationService.findApplicationByAnswer(answerId);
		result = this.findOne(answerId);

		Assert.notNull(result);
		Assert.isTrue(this.hackerService.findByPrincipal().equals(application.getHacker()));

		return result;
	}
	public Answer findOneToCompanyDisplay(final int answerId) {
		Application application;
		Answer result;

		application = this.applicationService.findApplicationByAnswer(answerId);
		result = this.findOne(answerId);

		Assert.notNull(result);
		Assert.isTrue(this.companyService.findByPrincipal().equals(application.getPosition().getCompany()));

		return result;
	}

	public Collection<Answer> findAll() {
		Collection<Answer> results;

		results = this.answerRepository.findAll();

		return results;
	}

	// Other business methods ---------------------

}
