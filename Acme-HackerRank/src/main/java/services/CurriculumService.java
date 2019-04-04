
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CurriculumRepository;

@Service
@Transactional
public class CurriculumService {

	// Managed repository ------------------------------------------------

	@Autowired
	private CurriculumRepository	curriculumRepository;


	// Other supporting services -----------------------------------------

	// Constructors ------------------------------------------------------

	public CurriculumService() {
		super();
	}

	// Simple CRUD methods -----------------------------------------------

	// Other business methods --------------------------------------------

	public Double[] findDataNumberCurriculumPerHacker() {
		Double[] result;

		result = this.curriculumRepository.findDataNumberCurriculumPerHacker();
		Assert.notNull(result);

		return result;
	}

	// Ancillary methods -------------------------------------------------

}
