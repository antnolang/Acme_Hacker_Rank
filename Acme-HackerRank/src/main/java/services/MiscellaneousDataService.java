
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.MiscellaneousDataRepository;

@Service
@Transactional
public class MiscellaneousDataService {

	// Managed repository ------------------------------------------------

	@Autowired
	private MiscellaneousDataRepository	miscellaneousDataRepository;


	// Other supporting services -----------------------------------------

	// Constructors ------------------------------------------------------

	public MiscellaneousDataService() {
		super();
	}

	// Simple CRUD methods -----------------------------------------------

	// Other business methods --------------------------------------------
}
