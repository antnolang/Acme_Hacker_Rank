
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



	@Autowired
	private UtilityService		utilityService;


	// Other supporting services -------------------


	// Constructors -------------------------------

	public PositionService() {
		super();
	}

	// Simple CRUD methods ------------------------

	public Position findOne(final int positionId) {
		Position result;

		result = this.positionRepository.findOne(positionId);
		Assert.notNull(result);

		return result;
	}

	public Position findOneToDisplay(final int positionId) {
		Position result;

		result = this.findOne(positionId);
		Assert.isTrue(result.getIsFinalMode() == true);
		Assert.isTrue(result.getIsCancelled() == false);

		return result;
	}

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

	public Collection<Position> findFinalModePositionsByCompany(final int companyId) {
		Collection<Position> result;

		result = this.positionRepository.findFinalModePositionsByCompany(companyId);


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
