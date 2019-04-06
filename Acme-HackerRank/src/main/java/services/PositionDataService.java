
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.PositionDataRepository;

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
}
