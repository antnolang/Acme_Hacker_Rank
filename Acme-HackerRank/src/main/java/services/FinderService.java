
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.FinderRepository;

@Service
@Transactional
public class FinderService {

	// Managed repository ------------------------------------------------

	@Autowired
	private FinderRepository	finderRepository;


	// Other supporting services -----------------------------------------

	// Constructors ------------------------------------------------------

	public FinderService() {
		super();
	}

	// Simple CRUD methods -----------------------------------------------

	// Other business methods --------------------------------------------

	public Double[] findDataNumberResultsFinder() {
		Double[] result;

		result = this.finderRepository.findDataNumberResultsFinder();
		Assert.notNull(result);

		return result;
	}
}
