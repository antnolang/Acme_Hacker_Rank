
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.PositionRepository;
import domain.Company;
import domain.Position;

@Service
@Transactional
public class PositionService {

	// Managed repository --------------------------

	@Autowired
	private PositionRepository	positionRepository;

	// Other supporting services -------------------

	@Autowired
	private CompanyService		companyService;

	@Autowired
	private UtilityService		utilityService;


	// Constructors -------------------------------

	public PositionService() {
		super();
	}

	// Simple CRUD methods ------------------------

	// Other business methods ---------------------

	public Position create() {
		Position result;
		Company company;

		result = new Position();
		company = this.companyService.findByPrincipal();

		result.setTicker("0000-XXXX");
		result.setCompany(company);
		result.setIsFinalMode(false);
		result.setIsCancelled(false);

		return result;
	}

	public Position save(final Position position) {
		Assert.notNull(position);
		this.checkByPrincipal(position);

		final Position result;

		if (position.getId() == 0)
			position.setTicker(this.utilityService.generateValidTicker(position.getTitle()));

		result = this.positionRepository.save(position);

		return result;
	}

	public Position findOne(final int positionId) {
		Position result;

		result = this.positionRepository.findOne(positionId);

		return result;
	}

	public Collection<Position> findAll() {
		Collection<Position> results;

		results = this.positionRepository.findAll();

		return results;
	}

	public Position findOneToEdit(final int positionId) {
		Position result;

		result = this.positionRepository.findOne(positionId);

		Assert.isTrue(!result.getIsFinalMode());

		return result;
	}

	public void delete(final Position position) {
		Assert.notNull(position);
		Assert.isTrue(this.positionRepository.exists(position.getId()));
		this.checkByPrincipal(position);

		this.positionRepository.delete(position);

	}

	//Other public methods  -----------------------------------------------
	public Collection<Position> findAllPositionAvaliable() {
		Collection<Position> result;

		result = this.positionRepository.findAllPositionAvaliable();

		return result;
	}

	public Collection<Position> findPositionByPrincipal() {
		Collection<Position> result;
		Company principal;

		principal = this.companyService.findByPrincipal();
		result = this.positionRepository.findPositionByCompany(principal.getId());

		return result;
	}

	// Protected methods -----------------------------------------------
	protected String existTicker(final String ticker) {
		String result;

		result = this.positionRepository.existTicker(ticker);

		return result;
	}

	// Private methods-----------------------------------------------
	private void checkByPrincipal(final Position position) {
		Company owner;
		Company principal;

		owner = position.getCompany();
		principal = this.companyService.findByPrincipal();

		Assert.isTrue(owner.equals(principal));
	}
}
