
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ProblemRepository;
import domain.Application;
import domain.Company;
import domain.Hacker;
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


	// Constructors -------------------------------

	public ProblemService() {
		super();
	}

	// Simple CRUD methods ------------------------

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

}
