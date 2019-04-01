
package services;

import javax.transaction.Transactional;
import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Customisation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class CustomisationServiceTest extends AbstractTest {

	// Service under test ----------------------------------------
	@Autowired
	private CustomisationService	customisationService;


	// Supporting services --------------------------------------

	// Suite Tests ----------------------------------------------

	/*
	 * A: level C: requirement 14 (The system must be easy to customise at run time).
	 * B: The business rule that is intended to be broken: customisation is null.
	 * C: Analysis of sentence coverage: 1/5 -> 20.00% of executed lines codes .
	 * D: Analysis of data coverage: intentionally blank.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void negative_saveTest_uno() {
		super.authenticate("admin1");

		final Customisation customisation = null;
		Customisation saved;

		saved = this.customisationService.save(customisation);

		Assert.isNull(saved);

		super.unauthenticate();
	}

	/*
	 * A: Requirement tested: level C: requirement 14 (The system must be easy to customise at run time).
	 * B: The business rule that is intended to be broken: An object of Customisation is inserted in DB.
	 * C: Analysis of sentence coverage: 2/5 -> 40.00% of executed lines codes .
	 * D: Analysis of data coverage: intentionally blank.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void negative_saveTest_dos() {
		super.authenticate("admin1");

		Customisation customisation, saved;

		customisation = new Customisation();
		customisation.setName("Acme Testing");
		customisation.setBanner("https://tinyurl.com/acme-handy-worker-logo");
		customisation.setWelcomeMessageEn("Hello world!!");
		customisation.setWelcomeMessageEs("Hola mundo!!");
		customisation.setCountryCode("+34");
		customisation.setMaxNumberResults(20);
		customisation.setTimeCachedResults(10);
		customisation.setSpamWords("caparros,monchi,del nido,castro");

		saved = this.customisationService.save(customisation);

		Assert.isNull(saved);

		super.unauthenticate();
	}

	@Test
	public void driverEdit() {
		final Object testingData[][] = {
			/*
			 * A: Requirement tested: level C: requirement 14 (The system must be easy to customise at run time)
			 * B: The business rule that is intended to be broken: invalid data in Customisation::name.
			 * C: Analysis of sentence coverage: 4/5 -> 80.00% executed code lines.
			 * D: Analysis of data coverage: Customisation::name is null => 1/31 -> 3.33%.
			 */
			{
				null, "https://i.imgur.com/7b8lu4b.png", "Hello world!!", "Hola mundo!!", "+43", 20, 50, "gilipollas,tonto,subnormal", ConstraintViolationException.class
			},
			/*
			 * A: Requirement tested: level C: requirement 14 (The system must be easy to customise at run time)
			 * B: The business rule that is intended to be broken: invalid data in Customisation::name.
			 * C: Analysis of sentence coverage: 4/5 -> 80.00% executed code lines.
			 * D: Analysis of data coverage: Customisation::name is empty string => 1/31 -> 3.33%.
			 */
			{
				"", "https://i.imgur.com/7b8lu4b.png", "Hello world!!", "Hola mundo!!", "+43", 20, 50, "gilipollas,tonto,subnormal", ConstraintViolationException.class
			}
		};

		for (int i = 0; i < testingData.length; i++)
			this.templateEdit((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (int) testingData[i][5], (int) testingData[i][6], (String) testingData[i][7],
				(Class<?>) testingData[i][8]);

	}

	protected void templateEdit(final String name, final String banner, final String welcomeMessageEn, final String welcomeMessageEs, final String countryCode, final int timeCachedResults, final int maxNumberResults, final String spamWords,
		final Class<?> expected) {
		Class<?> caught;
		Customisation found, saved;

		this.startTransaction();

		caught = null;
		try {
			super.authenticate("admin1");

			found = this.customisationService.find();
			found.setName(name);
			found.setBanner(banner);
			found.setWelcomeMessageEn(welcomeMessageEn);
			found.setWelcomeMessageEs(welcomeMessageEs);
			found.setCountryCode(countryCode);
			found.setTimeCachedResults(timeCachedResults);
			found.setMaxNumberResults(maxNumberResults);
			found.setSpamWords(spamWords);

			saved = this.customisationService.save(found);
			this.customisationService.flush();

			Assert.notNull(saved);
			Assert.isTrue(saved.getId() != 0);
		} catch (final Throwable oops) {
			caught = oops.getClass();
		} finally {
			super.unauthenticate();
		}

		this.rollbackTransaction();

		super.checkExceptions(expected, caught);
	}
}
