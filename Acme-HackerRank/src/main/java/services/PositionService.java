
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.PositionRepository;
import domain.Position;

@Service
@Transactional
public class PositionService {

	// Managed repository --------------------------

	@Autowired
	private PositionRepository	positionRepository;


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

	public List<Position> findPositionsBestWorstSalary() {
		final List<Position> result;

		final List<Position> positions = new ArrayList<>(this.positionRepository.findPositionsBestWorstSalary());
		result = new ArrayList<>();

		result.add(positions.get(0));
		result.add(positions.get(positions.size() - 1));

		return result;
	}

}
