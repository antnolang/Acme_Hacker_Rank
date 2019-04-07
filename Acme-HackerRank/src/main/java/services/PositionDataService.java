
package services;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.PositionDataRepository;
import domain.PositionData;

@Service
@Transactional
public class PositionDataService {

	// Managed repository ------------------------------------------------

	@Autowired
	private PositionDataRepository	positionDataRepository;


	// Other supporting services -----------------------------------------

	// Constructors ------------------------------------------------------

	public PositionDataService() {
		super();
	}

	// Simple CRUD methods -----------------------------------------------

	// Other business methods --------------------------------------------

	// Ancillary methods -------------------------------------------------

	protected Collection<PositionData> copy(final Collection<PositionData> positionData) {
		final Set<PositionData> result = new HashSet<>();

		for (final PositionData p : positionData) {
			final PositionData copy = new PositionData();

			copy.setDescription(p.getDescription());
			copy.setEndDate(p.getEndDate());
			copy.setStartDate(p.getStartDate());
			copy.setTitle(p.getTitle());

			result.add(copy);
		}

		return result;
	}
}
