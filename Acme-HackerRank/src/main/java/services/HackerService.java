
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.HackerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccountService;
import domain.Hacker;

@Service
@Transactional
public class HackerService {

	// Managed repository --------------------------

	@Autowired
	private HackerRepository	hackerRepository;

	// Other supporting services -------------------

	@Autowired
	private UserAccountService	userAccountService;

	@Autowired
	private ActorService		actorService;


	// Constructors -------------------------------

	public HackerService() {
		super();
	}

	// Simple CRUD methods ------------------------

	public Hacker create() {
		Hacker result;

		result = new Hacker();
		result.setUserAccount(this.userAccountService.createUserAccount(Authority.HACKER));

		return result;
	}

	public Hacker findOne(final int hackerId) {
		Hacker result;

		result = this.hackerRepository.findOne(hackerId);
		Assert.notNull(result);

		return result;
	}

	public Hacker findOneToDisplayEdit(final int hackerId) {
		Assert.isTrue(hackerId != 0);

		Hacker result, principal;

		principal = this.findByPrincipal();
		result = this.hackerRepository.findOne(hackerId);
		Assert.notNull(result);
		Assert.isTrue(principal.getId() == hackerId);

		return result;
	}

	public Hacker save(final Hacker hacker) {
		Hacker result;

		result = (Hacker) this.actorService.save(hacker);

		return result;
	}

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
