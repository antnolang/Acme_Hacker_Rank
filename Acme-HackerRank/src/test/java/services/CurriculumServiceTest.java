
package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import repositories.CurriculumRepository;
import utilities.AbstractTest;
import domain.Curriculum;
import domain.PersonalData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class CurriculumServiceTest extends AbstractTest {

	// Service under testing --------------------------------------------------

	@Autowired
	private CurriculumService		curriculumService;

	// Other services and repositories ----------------------------------------

	@Autowired
	private CurriculumRepository	curriculumRepository;


	// Tests ------------------------------------------------------------------

	/*
	 * A: An actor who is authenticated as an administrator must be able to
	 * display a dashboard with the following information:
	 * The minimum, the maximum, the average and the standard deviation of the
	 * number of curricula per hacker.
	 * 
	 * B: Positive test
	 * 
	 * C: 100% of sentence coverage.
	 * 
	 * D: 100% of data coverage.
	 */
	@Test
	public void testDataNumberCurriculumPerHacker() {
		Double[] data;

		data = this.curriculumService.findDataNumberCurriculumPerHacker();

		Assert.isTrue(data[0] == 0.0);
		Assert.isTrue(data[1] == 1.0);
		Assert.isTrue(data[2] == 0.8889);
		Assert.isTrue(data[3] == 0.3143);
	}

	/*
	 * A: An actor who is authenticated as a hacker must be able to: Manage his
	 * or her curricula, which includes listing, showing, CREATING, updating,
	 * and deleting them.
	 * 
	 * B: Positive test
	 * 
	 * C: TODO: Sentence coverage
	 * 
	 * D: TODO: Data coverage
	 */
	@Test
	public void curriculumCreateTest() {
		Curriculum curriculum, savedCurriculum;
		PersonalData personalData, savedPersonalData;
		String fullname, githubProf, linkedinProf, phone, statement, title;

		// Data
		title = "Curriculum test";
		fullname = "Hacker9 Rubio";
		githubProf = "https://www.github.com/antonio";
		linkedinProf = "https://www.linkedin.com/antonio";
		phone = "789654123";
		statement = "Statement test";

		super.authenticate("hacker9");

		curriculum = this.curriculumService.create();
		personalData = curriculum.getPersonalData();

		curriculum.setTitle(title);
		personalData.setFullname(fullname);
		personalData.setGithubProfile(githubProf);
		personalData.setLinkedInProfile(linkedinProf);
		personalData.setPhoneNumber(phone);
		personalData.setStatement(statement);

		savedCurriculum = this.curriculumService.save(curriculum);
		savedPersonalData = savedCurriculum.getPersonalData();

		super.unauthenticate();

		Assert.isTrue(savedCurriculum.getTitle() == title);
		Assert.isTrue(savedCurriculum.getEducationDatas().isEmpty() && savedCurriculum.getMiscellaneousDatas().isEmpty() && savedCurriculum.getPositionDatas().isEmpty());
		Assert.isTrue(savedPersonalData.getFullname() == fullname);
		Assert.isTrue(savedPersonalData.getGithubProfile() == githubProf);
		Assert.isTrue(savedPersonalData.getLinkedInProfile() == linkedinProf);
		Assert.isTrue(savedPersonalData.getPhoneNumber() == phone);
		Assert.isTrue(savedPersonalData.getStatement() == statement);
	}

	/*
	 * A: An actor who is authenticated as a hacker must be able to: Manage his
	 * or her curricula, which includes listing, showing, CREATING, updating,
	 * and deleting them.
	 * 
	 * B: Github profile must be a valid URL
	 * 
	 * C: TODO: Sentence coverage
	 * 
	 * D: TODO: Data coverage
	 */
	@Test(expected = IllegalArgumentException.class)
	public void curriculumCreateNegativeTest() {
		Curriculum curriculum, savedCurriculum;
		PersonalData personalData, savedPersonalData;
		String fullname, githubProf, linkedinProf, phone, statement, title;

		// Data
		title = "Curriculum test";
		fullname = "Hacker9 Rubio";
		githubProf = "antonio GithubProfile";
		linkedinProf = "https://www.linkedin.com/antonio";
		phone = "789654123";
		statement = "Statement test";

		super.authenticate("hacker9");

		curriculum = this.curriculumService.create();
		personalData = curriculum.getPersonalData();

		curriculum.setTitle(title);
		personalData.setFullname(fullname);
		personalData.setGithubProfile(githubProf);
		personalData.setLinkedInProfile(linkedinProf);
		personalData.setPhoneNumber(phone);
		personalData.setStatement(statement);

		savedCurriculum = this.curriculumService.save(curriculum);
		savedPersonalData = savedCurriculum.getPersonalData();

		super.unauthenticate();

		Assert.isTrue(savedCurriculum.getTitle() == title);
		Assert.isTrue(savedCurriculum.getEducationDatas().isEmpty() && savedCurriculum.getMiscellaneousDatas().isEmpty() && savedCurriculum.getPositionDatas().isEmpty());
		Assert.isTrue(savedPersonalData.getFullname() == fullname);
		Assert.isTrue(savedPersonalData.getGithubProfile() == githubProf);
		Assert.isTrue(savedPersonalData.getLinkedInProfile() == linkedinProf);
		Assert.isTrue(savedPersonalData.getPhoneNumber() == phone);
		Assert.isTrue(savedPersonalData.getStatement() == statement);
	}

	/*
	 * A: An actor who is authenticated as a hacker must be able to: Manage his
	 * or her curricula, which includes listing, showing, creating, UPDATING,
	 * and deleting them.
	 * 
	 * B: Positive test
	 * 
	 * C: TODO: Sentence coverage
	 * 
	 * D: TODO: Data coverage
	 */
	@Test
	public void curriculumEditTest() {
		Curriculum curriculum, saved;
		int curriculumId;
		String title;

		// Data
		title = "Curriculum edit test";

		super.authenticate("hacker8");

		curriculumId = super.getEntityId("curriculum81");
		curriculum = this.curriculumRepository.findOne(curriculumId);
		curriculum = this.cloneCurriculum(curriculum);

		curriculum.setTitle(title);
		saved = this.curriculumService.save(curriculum);

		super.unauthenticate();

		Assert.isTrue(saved.getTitle() == title);
	}

	/*
	 * A: An actor who is authenticated as a hacker must be able to: Manage his
	 * or her curricula, which includes listing, showing, creating, UPDATING,
	 * and deleting them.
	 * 
	 * B: The curriculum can only be updated by its owner.
	 * 
	 * C: TODO: Sentence coverage
	 * 
	 * D: TODO: Data coverage
	 */
	@Test(expected = IllegalArgumentException.class)
	public void curriculumEditNegativeTest() {
		Curriculum curriculum, saved;
		int curriculumId;
		String title;

		// Data
		title = "Curriculum edit test";

		super.authenticate("hacker9");

		curriculumId = super.getEntityId("curriculum81");
		curriculum = this.curriculumRepository.findOne(curriculumId);
		curriculum = this.cloneCurriculum(curriculum);

		curriculum.setTitle(title);
		saved = this.curriculumService.save(curriculum);

		super.unauthenticate();

		Assert.isTrue(saved.getTitle() == title);
	}

	/*
	 * A: An actor who is authenticated as a hacker must be able to: Manage his
	 * or her curricula, which includes listing, showing, creating, updating,
	 * and DELETING them.
	 * 
	 * B: Positive test
	 * 
	 * C: TODO: Sentence coverage
	 * 
	 * D: TODO: Data coverage
	 */
	@Test
	public void curriculumDeleteTest() {
		Curriculum curriculum, saved;
		int curriculumId;

		super.authenticate("hacker8");

		curriculumId = super.getEntityId("curriculum81");
		curriculum = this.curriculumRepository.findOne(curriculumId);

		this.curriculumService.delete(curriculum);

		super.unauthenticate();

		saved = this.curriculumRepository.findOne(curriculumId);
		Assert.isTrue(saved == null);
	}

	/*
	 * A: An actor who is authenticated as a hacker must be able to: Manage his
	 * or her curricula, which includes listing, showing, creating, updating,
	 * and DELETING them.
	 * 
	 * B: The curriculum can only be deleted by its owner.
	 * 
	 * C: TODO: Sentence coverage
	 * 
	 * D: TODO: Data coverage
	 */
	@Test(expected = IllegalArgumentException.class)
	public void curriculumDeleteNegativeTest() {
		Curriculum curriculum, saved;
		int curriculumId;

		super.authenticate("hacker9");

		curriculumId = super.getEntityId("curriculum81");
		curriculum = this.curriculumRepository.findOne(curriculumId);

		this.curriculumService.delete(curriculum);

		super.unauthenticate();

		saved = this.curriculumRepository.findOne(curriculumId);
		Assert.isTrue(saved == null);
	}

	// Ancillary methods --------------------------------------------------

	private Curriculum cloneCurriculum(final Curriculum curriculum) {
		final Curriculum res = new Curriculum();

		res.setEducationDatas(curriculum.getEducationDatas());
		res.setHacker(curriculum.getHacker());
		res.setId(curriculum.getId());
		res.setIsOriginal(curriculum.getIsOriginal());
		res.setMiscellaneousDatas(curriculum.getMiscellaneousDatas());
		res.setPersonalData(curriculum.getPersonalData());
		res.setPositionDatas(curriculum.getPositionDatas());
		res.setTitle(curriculum.getTitle());
		res.setVersion(curriculum.getVersion());

		return res;
	}

}
