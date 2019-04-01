
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.HackerRepository;
import security.LoginService;
import domain.Hacker;

@Service
@Transactional
public class HackerService {

	// Managed repository --------------------------

	@Autowired
	private HackerRepository	hackerRepository;


	// Other supporting services -------------------

	// Constructors -------------------------------

	public HackerService() {
		super();
	}

	// Simple CRUD methods ------------------------

	// Other business methods ---------------------

	public Hacker findByPrincipal() {
		Hacker result;
		int userAccountId;

		userAccountId = LoginService.getPrincipal().getId();

		result = this.findHackerByUserAccount(userAccountId);
		Assert.notNull(result);

		return result;
	}

	private Hacker findHackerByUserAccount(final int id) {
		Hacker result;

		result = this.hackerRepository.findHackerByUserAccount(id);

		return result;
	}

}
