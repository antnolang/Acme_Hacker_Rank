
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PersonalDataRepository;
import domain.Hacker;
import domain.PersonalData;

@Service
@Transactional
public class PersonalDataService {

	// Managed repository ------------------------------------------------

	@Autowired
	private PersonalDataRepository	personalDataRepository;

	// Other supporting services -----------------------------------------

	@Autowired
	private HackerService			hackerService;


	// Constructors ------------------------------------------------------

	public PersonalDataService() {
		super();
	}

	// Simple CRUD methods -----------------------------------------------

	protected PersonalData create(final String fullname) {
		PersonalData result;

		result = new PersonalData();
		result.setFullname(fullname);

		return result;
	}

	public PersonalData save(final PersonalData personalData) {
		Assert.notNull(personalData);
		Assert.isTrue(this.personalDataRepository.exists(personalData.getId()));
		this.checkOwner(personalData.getId());

		final PersonalData saved = this.personalDataRepository.save(personalData);

		return saved;
	}

	private PersonalData findOne(final int personalDataId) {
		PersonalData result;

		result = this.personalDataRepository.findOne(personalDataId);
		Assert.notNull(result);

		return result;
	}

	// Other business methods --------------------------------------------

	public PersonalData findOneToEdit(final int personalDataId) {
		PersonalData result;

		result = this.findOne(personalDataId);
		this.checkOwner(personalDataId);

		return result;
	}

	// Ancillary methods -------------------------------------------------

	protected PersonalData copy(final PersonalData personalData) {
		final PersonalData res = new PersonalData();

		res.setFullname(personalData.getFullname());
		res.setGithubProfile(personalData.getGithubProfile());
		res.setLinkedInProfile(personalData.getLinkedInProfile());
		res.setPhoneNumber(personalData.getPhoneNumber());
		res.setStatement(personalData.getStatement());

		return res;
	}

	private void checkOwner(final int personalDataId) {
		Hacker principal, owner;

		principal = this.hackerService.findByPrincipal();
		owner = this.hackerService.findByPersonalDataId(personalDataId);

		Assert.isTrue(principal.equals(owner));
	}
}
