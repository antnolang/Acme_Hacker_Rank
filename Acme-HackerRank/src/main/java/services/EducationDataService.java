
package services;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.EducationDataRepository;
import domain.EducationData;

@Service
@Transactional
public class EducationDataService {

	// Managed repository ------------------------------------------------

	@Autowired
	private EducationDataRepository	educationDataRepository;


	// Other supporting services -----------------------------------------

	// Constructors ------------------------------------------------------

	public EducationDataService() {
		super();
	}

	// Simple CRUD methods -----------------------------------------------

	// Other business methods --------------------------------------------

	// Ancillary methods -------------------------------------------------

	protected Collection<EducationData> copy(final Collection<EducationData> educationDatas) {
		final Set<EducationData> result = new HashSet<>();

		for (final EducationData e : educationDatas) {
			final EducationData copy = new EducationData();

			copy.setDegree(e.getDegree());
			copy.setEndDate(e.getEndDate());
			copy.setId(e.getId());
			copy.setInstitution(e.getInstitution());
			copy.setMark(e.getMark());
			copy.setStartDate(e.getStartDate());
			copy.setVersion(e.getVersion());

			result.add(copy);
		}

		return result;
	}
}
