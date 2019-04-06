
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.EducationDataRepository;

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
}
