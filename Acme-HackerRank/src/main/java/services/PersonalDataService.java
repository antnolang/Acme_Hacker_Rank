
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import repositories.PersonalDataRepository;
import domain.PersonalData;

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
}
