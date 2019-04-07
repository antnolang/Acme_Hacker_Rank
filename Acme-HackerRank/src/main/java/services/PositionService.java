
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import repositories.PositionRepository;
import domain.Company;
import domain.Finder;
import domain.Position;
import domain.Problem;

@Service
@Transactional
public class PositionService {

	// Managed repository --------------------------

	@Autowired
	private PositionRepository	positionRepository;

	@Autowired
	private CompanyService		companyService;

	@Autowired
	private ProblemService		problemService;

	@Autowired
	private UtilityService		utilityService;

	@Autowired
	private Validator			validator;


	// Other supporting services -------------------

	// Constructors -------------------------------

	public PositionService() {
		super();
	}

	// Simple CRUD methods ------------------------

	public Position create() {
		Position result;
		Company company;

		result = new Position();
		company = this.companyService.findByPrincipal();

		result.setTicker("XXXX-0000");
		result.setCompany(company);
		result.setIsFinalMode(false);
		result.setIsCancelled(false);
		result.setProblems(Collections.<Problem> emptySet());

		return result;
	}

	public Position save(final Position position) {
		Assert.notNull(position);
		this.checkByPrincipal(position);
		Assert.isTrue(position.getDeadline().after(this.utilityService.current_moment()));

		final Position result;

		result = this.positionRepository.save(position);

		return result;
	}

	public void delete(final Position position) {
		Assert.notNull(position);
		Assert.isTrue(this.positionRepository.exists(position.getId()));
		this.checkByPrincipal(position);

		this.positionRepository.delete(position);
	}

	public Position findOne(final int positionId) {
		Position result;

		result = this.positionRepository.findOne(positionId);
		Assert.notNull(result);

		return result;
	}

	public Position findOneToDisplay(final int positionId) {
		Position result;

		result = this.findOne(positionId);
		Assert.isTrue(result.getIsFinalMode());

		return result;
	}

	public Collection<Position> findAll() {
		Collection<Position> results;

		results = this.positionRepository.findAll();

		return results;
	}

	public Position findOneToEditDelete(final int positionId) {
		Position result;

		result = this.positionRepository.findOne(positionId);
		this.checkByPrincipal(result);
		Assert.isTrue(!result.getIsFinalMode());

		Assert.notNull(result);

		return result;
	}
	public Position findOneFinalByPrincipal(final int positionId) {
		Position result;

		result = this.positionRepository.findOne(positionId);
		this.checkByPrincipal(result);
		Assert.isTrue(result.getIsFinalMode());

		return result;
	}

	public void makeFinal(final Position position) {
		Collection<Problem> problems;

		problems = position.getProblems();

		Assert.isTrue(problems.size() >= 2);
		this.checkByPrincipal(position);

		position.setIsFinalMode(true);
	}
	public void cancel(final Position position) {
		this.checkByPrincipal(position);
		Assert.isTrue(position.getIsFinalMode());

		position.setIsCancelled(true);
	}
	//Other public methods  -----------------------------------------------

	public Collection<Position> searchByKeyword(final String keyword) {
		Collection<Position> positions;

		positions = this.positionRepository.findAvailableByKeyword(keyword);
		Assert.notNull(positions);

		return positions;
	}

	public Collection<Position> findAllPositionAvailable() {
		Collection<Position> result;

		result = this.positionRepository.findAllPositionAvailable();

		return result;
	}
	public Collection<Position> findFinalModePositionsByCompany(final int companyId) {
		Collection<Position> result;

		result = this.positionRepository.findFinalModePositionsByCompany(companyId);

		return result;
	}

	public Double[] findDataSalaryOffered() {
		Double[] result;

		result = this.positionRepository.findDataSalaryOffered();
		Assert.notNull(result);

		return result;

	}

	public Collection<Position> findPositionByPrincipal() {
		Collection<Position> result;
		Company principal;

		principal = this.companyService.findByPrincipal();
		result = this.positionRepository.findPositionByCompany(principal.getId());

		return result;
	}

	public List<Position> findPositionsBestWorstSalary() {
		final List<Position> result;

		final List<Position> positions = new ArrayList<>(this.positionRepository.findPositionsBestWorstSalary());
		result = new ArrayList<>();

		result.add(positions.get(0));
		result.add(positions.get(positions.size() - 1));

		return result;

	}

	public Double[] findDataNumberPositionsPerCompany() {
		Double[] result;

		result = this.positionRepository.findDataNumberPositionsPerCompany();

		return result;
	}
	// This method id used when an actor want to delete all his or her data.
	public void deleteByCompany(final Company company) {
		Collection<Position> positions;

		positions = this.positionRepository.findPositionByCompany(company.getId());

		this.positionRepository.delete(positions);

	}
	public Collection<Position> findPositionsByProblem(final Problem problem) {
		Collection<Position> positions;

		positions = this.positionRepository.findPositionsByProblem(problem.getId());

		return positions;
	}

	public Boolean hasProblem(final Position position) {
		Collection<Problem> problems;
		Boolean result;

		if (position.getId() == 0)
			result = false;
		else {
			problems = this.problemService.findProblemsByPrincipal();
			result = problems.size() >= 2;
		}

		return result;
	}
	// Protected methods -----------------------------------------------
	protected String existTicker(final String ticker) {
		String result;

		result = this.positionRepository.existTicker(ticker);

		return result;
	}

	protected void searchPositionFinder(final Finder finder, final Pageable pageable) {
		Page<Position> positions;

		positions = this.positionRepository.searchPositionFinder(finder.getKeyword(), finder.getDeadline(), finder.getMaximumDeadline(), finder.getMinimumSalary(), pageable);
		Assert.notNull(positions);

		finder.setPositions(positions.getContent());
		finder.setUpdatedMoment(this.utilityService.current_moment());
	}

	protected void flush() {
	}

	// Private methods-----------------------------------------------
	private void checkByPrincipal(final Position position) {
		Company owner;
		Company principal;

		owner = position.getCompany();
		principal = this.companyService.findByPrincipal();

		Assert.isTrue(owner.equals(principal));
	}

	// Reconstruct ----------------------------------------------
	public Position reconstruct(final Position position, final BindingResult binding) {
		Position result, positionStored;

		if (position.getId() != 0) {
			result = new Position();
			positionStored = this.findOne(position.getId());
			result.setId(positionStored.getId());
			result.setCompany(positionStored.getCompany());
			result.setIsFinalMode(false);
			result.setIsCancelled(false);
			result.setTicker(positionStored.getTicker());
			result.setVersion(positionStored.getVersion());

		} else {
			result = this.create();
			result.setTicker(this.utilityService.generateValidTicker(position.getTitle()));
		}

		result.setDeadline(position.getDeadline());
		result.setDescription(position.getDescription());
		result.setProfile(position.getProfile());
		result.setSalary(position.getSalary());
		result.setSkills(position.getSkills());
		result.setTechnologies(position.getTechnologies());
		result.setTitle(position.getTitle());
		result.setProblems(position.getProblems());

		this.checkDeadline(result, binding);

		this.validator.validate(result, binding);

		return result;
	}

	public void checkDeadline(final Position position, final BindingResult binding) {
		if (position.getDeadline() != null)
			if (position.getDeadline().before(this.utilityService.current_moment()))
				binding.rejectValue("deadline", "position.commit.deadline", "Deadline must be in the future");
	}

}
