
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.ProblemRepository;
import domain.Application;
import domain.Company;
import domain.Hacker;
import domain.Position;
import domain.Problem;

@Service
@Transactional
public class ProblemService {

	// Managed repository --------------------------

	@Autowired
	private ProblemRepository	problemRepository;

	// Other supporting services -------------------

	@Autowired
	private CompanyService		companyService;

	@Autowired
	private ApplicationService	applicationService;

	@Autowired
	private HackerService		hackerService;

	@Autowired
	private Validator			validator;


	// Constructors -------------------------------

	public ProblemService() {
		super();
	}

	// Simple CRUD methods ------------------------

	public Problem create() {
		Problem result;
		Company company;

		result = new Problem();
		company = this.companyService.findByPrincipal();

		result.setCompany(company);
		result.setIsFinalMode(false);

		return result;
	}

	public Problem save(final Problem problem) {
		Assert.notNull(problem);
		this.checkByPrincipal(problem);
		Assert.isTrue(!problem.getIsFinalMode());

		final Problem result;

		result = this.problemRepository.save(problem);

		return result;
	}

	public void delete(final Problem problem) {
		Assert.notNull(problem);
		Assert.isTrue(this.problemRepository.exists(problem.getId()));
		this.checkByPrincipal(problem);

		this.problemRepository.delete(problem);
	}

	public Problem findOneToEditDelete(final int problemId) {
		Problem result;

		result = this.problemRepository.findOne(problemId);
		this.checkByPrincipal(result);
		Assert.isTrue(!result.getIsFinalMode());

		Assert.notNull(result);

		return result;
	}

	public Problem findOne(final int problemId) {
		Problem result;

		result = this.problemRepository.findOne(problemId);
		Assert.notNull(result);

		return result;
	}

	public Problem findOneToPrincipal(final int problemId) {
		Problem result;

		result = this.problemRepository.findOne(problemId);
		this.checkByPrincipal(result);
		Assert.notNull(result);

		return result;
	}

	public Problem findOneToDisplayHacker(final int problemId) {
		Problem result;
		final Collection<Application> applicationsProblemHacker;
		Hacker principal;

		result = this.problemRepository.findOne(problemId);
		principal = this.hackerService.findByPrincipal();
		applicationsProblemHacker = this.applicationService.findApplicationsByProblemHacker(problemId, principal.getId());

		Assert.isTrue(!applicationsProblemHacker.isEmpty());
		Assert.notNull(result);

		return result;
	}

	public Collection<Problem> findProblemsByPrincipal() {
		final Collection<Problem> problems;
		Company principal;

		principal = this.companyService.findByPrincipal();
		problems = this.problemRepository.findByCompany(principal.getId());

		Assert.notNull(problems);

		return problems;
	}
	// Other business methods ---------------------

	// Private methods-----------------------------------------------
	private void checkByPrincipal(final Problem problem) {
		Company owner;
		Company principal;

		owner = problem.getCompany();
		principal = this.companyService.findByPrincipal();

		Assert.isTrue(owner.equals(principal));
	}
	// Reconstruct ----------------------------------------------
	public Problem reconstruct(final Problem problem, final BindingResult binding) {
		Problem result, problemStored;

		if (problem.getId() != 0) {
			result = new Problem();
			problemStored = this.findOne(problem.getId());
			result.setAttachments(problemStored.getAttachments());
			result.setCompany(problemStored.getCompany());
			result.setHint(problemStored.getHint());
			result.setIsFinalMode(problemStored.getIsFinalMode());
			result.setPosition(problemStored.getPosition());
			result.setStatement(problemStored.getStatement());
			result.setTitle(problemStored.getTitle());

		} else
			result = this.create();
		result.setId(problem.getId());
		result.setAttachments(problem.getAttachments());
		result.setHint(problem.getHint());
		result.setStatement(problem.getStatement());
		result.setTitle(problem.getTitle());

		this.validator.validate(result, binding);

		return result;
	}

	public List<Problem> problemsWithoutAcceptedApplicationWithoutOwnApplication(final Position position, final Hacker hacker) {
		Collection<Problem> problemsAll;
		Collection<Problem> problemsBusy;
		List<Problem> problemsFree;

		problemsAll = this.problemRepository.positionProblem(position.getId());
		problemsBusy = this.problemRepository.problemsWithAcceptedApplicationWithOwnApplication(position.getId(), hacker.getId());

		problemsAll.removeAll(problemsBusy);

		problemsFree = new ArrayList<Problem>(problemsAll);

		return problemsFree;
	}

}
