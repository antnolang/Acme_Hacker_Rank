
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.PersonalDataRepository;

@Service
@Transactional
public class PersonalDataService {

	// Managed repository ------------------------------------------------

	@Autowired
	private PersonalDataRepository	personalDataRepository;


	// Other supporting services -----------------------------------------

	// Constructors ------------------------------------------------------

	public PersonalDataService() {
		super();
	}

	// Simple CRUD methods -----------------------------------------------

	// Other business methods --------------------------------------------
}
