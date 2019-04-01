
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import repositories.ProblemRepository;

@Service
@Transactional
public class ProblemService {

	// Managed repository --------------------------

	@Autowired
	private ProblemRepository	problemRepository;

	// Other supporting services -------------------

	@Autowired
	private CompanyService		companyService;


	// Constructors -------------------------------

	public ProblemService() {
		super();
	}

	// Simple CRUD methods ------------------------

	// Other business methods ---------------------

}
