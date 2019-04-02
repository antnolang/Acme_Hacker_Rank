
package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.SocialProfile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class SocialProfileServiceTest extends AbstractTest {

	// Service under testing ---------------------------
	@Autowired
	private SocialProfileService	socialProfileService;


	// Other supporting tests ---------------------------

	// Suite test ---------------------------------------

	/*
	 * A: level C: requirement 23.1 (A user can listing social profiles).
	 * C: Analysis of sentence coverage: 9/9 -> 100.00% of executed lines codes .
	 * D: Analysis of data coverage: intentionally blank.
	 */
	@Test
	public void findSocialProfilesByActor_positiveTest() {
		super.authenticate("admin1");

		int actorId;
		Collection<SocialProfile> social_profiles;

		actorId = super.getEntityId("administrator1");

		social_profiles = this.socialProfileService.findSocialProfilesByActor(actorId);

		Assert.notNull(social_profiles);
		Assert.notEmpty(social_profiles);

		super.unauthenticate();
	}

	/*
	 * A: level C: requirement 23.1 (A user can listing social profiles).
	 * B: The business rule that is intended to be broken: the social profiles belong to an administrator.
	 * C: Analysis of sentence coverage: 7/9 -> 77.78% of executed lines codes .
	 * D: Analysis of data coverage: intentionally blank.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void findSocialProfilesByActor_negativeTest() {
		super.authenticate("hacker1");

		int actorId;
		Collection<SocialProfile> social_profiles;

		actorId = super.getEntityId("administrator1");

		social_profiles = this.socialProfileService.findSocialProfilesByActor(actorId);

		Assert.notNull(social_profiles);
		Assert.notEmpty(social_profiles);

		super.unauthenticate();
	}

}
