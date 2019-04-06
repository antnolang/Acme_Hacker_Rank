
package services;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;

import repositories.ApplicationRepository;
import domain.Answer;
import domain.Application;
import domain.Curriculum;
import domain.Hacker;
import domain.Position;
import domain.Problem;

@Service
@Transactional
public class ApplicationService {

	// Managed repository ---------------------------------------------
	@Autowired
	private ApplicationRepository	applicationRepository;

	// Supporting services -------------------------------------------

	@Autowired
	private ProblemService			problemService;

	@Autowired
	private HackerService			hackerService;

	@Autowired
	private CompanyService			companyService;

	@Autowired
	private UtilityService			utilityService;


	//Constructor ----------------------------------------------------
	public ApplicationService() {
		super();
	}

	//Simple CRUD methods -------------------------------------------
	public Application create(final Position position) {
		Assert.isTrue(position.getIsFinalMode());
		Assert.isTrue(!(position.getIsCancelled()));
		Assert.isTrue(!(this.hackerService.originalCurricula().isEmpty()));
		Application result;
		Hacker hacker;
		Date moment;
		final Curriculum curriculum;
		final Problem problem;

		hacker = this.hackerService.findByPrincipal();

		Assert.isTrue(this.applicationRepository.findApplicationsByPositionByHacker(position.getId(), hacker.getId()).isEmpty());

		moment = this.utilityService.current_moment();
		curriculum = this.hackerService.originalCurricula().get(0);
		problem = this.getRandomProblem(this.problemService.problemsPosition(position));

		result = new Application();
		result.setHacker(hacker);
		result.setCurriculum(curriculum);
		result.setProblem(problem);
		result.setPosition(position);
		result.setStatus("PENDING");
		result.setApplicationMoment(moment);

		return result;
	}
	public Application save(final Application application) {
		Assert.notNull(application);
		Assert.isTrue(application.getPosition().getIsFinalMode());
		Assert.isTrue(!(application.getPosition().getIsCancelled()));
		Assert.isTrue(this.hackerService.findByPrincipal().equals(application.getHacker()));
		Assert.isTrue(application.getStatus().equals("PENDING"));

		Application result;
		Application applicationSaved;

		applicationSaved = this.applicationRepository.findOne(application.getId());

		if (application.getId() == 0) {
			Assert.isTrue(this.applicationRepository.findApplicationsByPositionByHacker(application.getPosition().getId(), application.getHacker().getId()).isEmpty());
			Assert.isTrue(this.problemService.problemsPosition(application.getPosition()).contains(application.getProblem()));
			Assert.isTrue(!(this.hackerService.originalCurricula().isEmpty()));
			//TODO comprobar copia de curriculum
			Assert.isTrue(application.getCurriculum().getHacker().equals(this.hackerService.findByPrincipal()));
			Assert.isTrue(!(application.getCurriculum().getIsOriginal()));
			Assert.isNull(application.getSubmittedMoment());
			Assert.isTrue(!(application.getApplicationMoment().equals(null)));
			Assert.isNull(application.getAnswer());
		} else {
			Assert.isTrue(applicationSaved.getProblem().equals(application.getProblem()));
			Assert.isTrue(applicationSaved.getPosition().equals(application.getPosition()));
			Assert.isTrue(applicationSaved.getHacker().equals(application.getHacker()));
			Assert.isTrue(applicationSaved.getCurriculum().equals(application.getCurriculum()));
			Assert.isTrue(applicationSaved.getApplicationMoment().equals(application.getApplicationMoment()));
			Assert.isNull(application.getSubmittedMoment());
			Assert.isTrue(!(application.getAnswer().equals(null)));

			Date moment;
			moment = this.utilityService.current_moment();

			application.setSubmittedMoment(moment);
			application.setStatus("SUBMITTED");
		}

		result = this.applicationRepository.save(application);

		return result;
	}

	public void acceptedApplication(final Application application) {
		Assert.isTrue(this.companyService.findByPrincipal().equals(application.getPosition().getCompany()));
		Assert.isTrue(application.getStatus().equals("SUBMITTED"));
		Assert.isTrue(!(application.getAnswer().equals(null)));
		Assert.isTrue(!(application.getSubmittedMoment().equals(null)));
		application.setStatus("ACCEPTED");
	}

	public void rejectedApplication(final Application application) {
		Assert.isTrue(this.companyService.findByPrincipal().equals(application.getPosition().getCompany()));
		Assert.isTrue(application.getStatus().equals("SUBMITTED"));
		Assert.isTrue(!(application.getAnswer().equals(null)));
		Assert.isTrue(!(application.getSubmittedMoment().equals(null)));
		application.setStatus("REJECTED");
	}

	protected void addAnswer(final Application application, final Answer answer) {
		application.setAnswer(answer);
	}

	public Application findOne(final int applicationId) {
		Application result;

		result = this.applicationRepository.findOne(applicationId);

		return result;
	}

	public Application findOneToHacker(final int applicationId) {
		Application result;

		result = this.findOne(applicationId);

		Assert.notNull(result);
		Assert.isTrue(this.hackerService.findByPrincipal().equals(result.getHacker()));

		return result;
	}

	public Application findOneToCompany(final int applicationId) {
		Application result;

		result = this.findOne(applicationId);

		Assert.notNull(result);
		Assert.isTrue(this.companyService.findByPrincipal().equals(result.getPosition().getCompany()));

		return result;
	}

	public Collection<Application> findAll() {
		Collection<Application> results;

		results = this.applicationRepository.findAll();

		return results;
	}

	// Reconstruct ----------------------------------------------
	public Application reconstruct(final Application application, final BindingResult binding) {
		Application result, applicationStored;

		if (application.getId() != 0) {
			result = new Application();
			applicationStored = this.findOne(application.getId());
			result.setId(applicationStored.getId());
			result.setApplicationMoment(applicationStored.getApplicationMoment());
			result.setStatus(applicationStored.getStatus());
			result.setCurriculum(applicationStored.getCurriculum());
			result.setPosition(applicationStored.getPosition());
			result.setProblem(applicationStored.getProblem());

			result.setSubmittedMoment(application.getSubmittedMoment());
			result.setAnswer(application.getAnswer());

		} else {
			result = this.create(application.getPosition());
			result.setCurriculum(application.getCurriculum());
		}

		return result;
	}

	// Other business methods ---------------------

	public Problem getRandomProblem(final List<Problem> problems) {
		return problems.get(new Random().nextInt(problems.size()));
	}

	public Double[] findDataNumberApplicationPerHacker() {
		Double[] result;

		result = this.applicationRepository.findDataNumberApplicationPerHacker();
		Assert.notNull(result);

		return result;
	}

	protected Collection<Application> findApplicationsByProblemHacker(final int idProblem, final int idHacker) {
		Collection<Application> result;

		result = this.findApplicationsByProblemHacker(idProblem, idHacker);

		return result;
	}

	public Collection<Application> findPendingApplicationsByHacker() {
		Collection<Application> applications;
		Hacker hacker;

		hacker = this.hackerService.findByPrincipal();
		applications = this.applicationRepository.findPendingApplicationsByHacker(hacker.getId());

		return applications;
	}

	public Collection<Application> findSubmittedApplicationsByHacker() {
		Collection<Application> applications;
		Hacker hacker;

		hacker = this.hackerService.findByPrincipal();
		applications = this.applicationRepository.findSubmittedApplicationsByHacker(hacker.getId());

		return applications;
	}

	public Collection<Application> findAcceptedApplicationsByHacker() {
		Collection<Application> applications;
		Hacker hacker;

		hacker = this.hackerService.findByPrincipal();
		applications = this.applicationRepository.findAcceptedApplicationsByHacker(hacker.getId());

		return applications;
	}

	public Collection<Application> findRejectedApplicationsByHacker() {
		Collection<Application> applications;
		Hacker hacker;

		hacker = this.hackerService.findByPrincipal();
		applications = this.applicationRepository.findRejectedApplicationsByHacker(hacker.getId());

		return applications;
	}

}
