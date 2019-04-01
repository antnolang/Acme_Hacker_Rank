
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.PositionRepository;

@Service
@Transactional
public class PositionService {

	// Managed repository --------------------------

	@Autowired
	private PositionRepository	positionRepository;

	// Other supporting services -------------------

	@Autowired
	private CompanyService		companyService;


	// Constructors -------------------------------

	public PositionService() {
		super();
	}

	// Simple CRUD methods ------------------------

	// Other business methods ---------------------

}
