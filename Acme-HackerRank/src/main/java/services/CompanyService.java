
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CompanyRepository;
import security.Authority;
import security.LoginService;
import security.UserAccountService;
import domain.Company;

@Service
@Transactional
public class CompanyService {

	// Managed repository --------------------------

	@Autowired
	private CompanyRepository	companyRepository;

	// Other supporting services -------------------

	@Autowired
	private UserAccountService	userAccountService;

	@Autowired
	private ActorService		actorService;


	// Constructors -------------------------------

	public CompanyService() {
		super();
	}

	// Simple CRUD methods ------------------------

	public Company create() {
		Company result;

		result = new Company();
		result.setUserAccount(this.userAccountService.createUserAccount(Authority.COMPANY));

		return result;
	}

	public Company findOne(final int companyId) {
		Company result;

		result = this.companyRepository.findOne(companyId);
		Assert.notNull(result);

		return result;
	}

	public Company findOneToDisplayEdit(final int companyId) {
		Assert.isTrue(companyId != 0);

		Company result, principal;

		principal = this.findByPrincipal();
		result = this.companyRepository.findOne(companyId);
		Assert.notNull(result);
		Assert.isTrue(principal.getId() == companyId);

		return result;
	}

	public Company save(final Company company) {
		Company result;

		result = (Company) this.actorService.save(company);

		return result;
	}

	// Other business methods ---------------------

	public Company findByPrincipal() {
		Company result;
		int userAccountId;

		userAccountId = LoginService.getPrincipal().getId();

		result = this.findCompanyByUserAccount(userAccountId);
		Assert.notNull(result);

		return result;
	}

	private Company findCompanyByUserAccount(final int id) {
		Company result;

		result = this.companyRepository.findCompanyByUserAccount(id);

		return result;
	}

}
