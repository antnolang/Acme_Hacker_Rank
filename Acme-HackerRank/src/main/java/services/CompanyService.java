
package services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CompanyRepository;
import security.LoginService;
import domain.Company;

@Service
@Transactional
public class CompanyService {

	// Managed repository --------------------------

	@Autowired
	private CompanyRepository	companyRepository;


	// Other supporting services -------------------

	// Constructors -------------------------------

	public CompanyService() {
		super();
	}

	// Simple CRUD methods ------------------------

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
