
package services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Position;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class PositionServiceTest extends AbstractTest {

	// Service under testing -----------------------------------

	@Autowired
	private PositionService	positionService;


	// Other services ------------------------------------------

	// Suite test ---------------------------------------------

	// TODO: Tests funcionales req 7.4
	//	/*
	//	 * A: An actor who is not authenticated must be able to: Search for a
	//	 * position using a single key word that must be contained in its title,
	//	 * its description, its profile, its skills, its technologies, or the
	//	 * name of the corresponding company.
	//	 * 
	//	 * B: Positive test
	//	 * 
	//	 * C: TODO: Sentence coverage
	//	 * 
	//	 * D: TODO: Data coverage
	//	 */
	//	@Test
	//	public void searchByKeywordTest() {
	//		Collection<Position> results;
	//		Position position;
	//		int positionId;
	//
	//		positionId = super.getEntityId("position2");
	//		position = this.positionRepository.findOne(positionId);
	//		results = this.positionService.searchByKeyword("company2");
	//
	//		Assert.isTrue(results.size() == 1);
	//		Assert.isTrue(results.contains(position));
	//	}
	//
	//	/*
	//	 * A: An actor who is not authenticated must be able to: Search for a
	//	 * position using a single key word that must be contained in its title,
	//	 * its description, its profile, its skills, its technologies, or the
	//	 * name of the corresponding company.
	//	 * 
	//	 * B: The positions are not available publicly until they are saved in `
	//	 * final mode.
	//	 * 
	//	 * C: TODO: Sentence coverage
	//	 * 
	//	 * D: TODO: Data coverage
	//	 */
	//	@Test(expected = IllegalArgumentException.class)
	//	public void searchByKeywordNegativeTest() {
	//		Collection<Position> results;
	//		Position position;
	//		int positionId;
	//
	//		positionId = super.getEntityId("position3"); // Position in draft mode
	//		position = this.positionRepository.findOne(positionId);
	//		results = this.positionService.searchByKeyword("company2");
	//
	//		Assert.isTrue(!results.contains(position));
	//	}

	/*
	 * A: An actor who is authenticated as an administrator muest be able to:
	 * Display a dashboard with the following information:
	 * The average, the minimum, the maximum, and the standard deviation of the number of positions per company.
	 * 
	 * B: Positive test
	 * 
	 * C: 100% of sentence coverage
	 * 
	 * D: 100% of data coverage
	 */
	@Test
	public void testfindDataNumberPositionsPerCompany() {
		Double[] data;

		data = this.positionService.findDataNumberPositionsPerCompany();

		Assert.isTrue(data[0] == 3.0);
		Assert.isTrue(data[1] == 2.0);
		Assert.isTrue(data[2] == 4.0);
		Assert.isTrue(data[3] == 0.8165);

	}

	/*
	 * A: An actor who is authenticated as an administrator muest be able to:
	 * Display a dashboard with the following information:
	 * The average, the minimum, the maximum, and the standard deviation of the
	 * salaries offered.
	 * 
	 * B: Positive test
	 * 
	 * C: 100% of sentence coverage
	 * 
	 * D: 100% of data coverage
	 */
	@Test
	public void findDataSalaryOffered() {
		Double[] data;

		data = this.positionService.findDataSalaryOffered();

		Assert.isTrue(data[0] == 2908.452222222222);
		Assert.isTrue(data[1] == 1586.23);
		Assert.isTrue(data[2] == 3586.23);
		Assert.isTrue(data[3] == 603.2831982443657);

	}

	/*
	 * A: An actor who is authenticated as an administrator muest be able to:
	 * Display a dashboard with the following information:
	 * The average, the minimum, the maximum, and the standard deviation of the
	 * salaries offered.
	 * 
	 * B: Positive test
	 * 
	 * C: 100% of sentence coverage
	 * 
	 * D: 100% of data coverage
	 */
	@Test
	public void findPositionsBestWorstSalary() {
		List<Position> data;

		data = this.positionService.findPositionsBestWorstSalary();

		Assert.isTrue(data.size() == 2.0);

	}

	@Test
	public void driverCreate() {
		final Object testingData[][] = {
			/*
			 * A:Req 9.1 Create a position, all datas
			 * C:
			 * D:
			 */
			{
				"company1", "titleNuevo", "descriptionNuevo", "2020-02-02", "profileNuevo", "skillsNuevo", "technologiesNuevo", 22.3, null
			},

			/*
			 * A:Req 9.1 Create a position, title in blank
			 * C:
			 * D:
			 */
			{
				"company1", "", "descriptionNuevo", "2020-02-02", "profileNuevo", "skillsNuevo", "technologiesNuevo", 22.3, null
			},
		/*
		 * A:Req 9.1 Create a position, description in blank
		 * C:
		 * D:
		 */
		//			{
		//				"company1", "titleNuevo", "", "2020-02-02", "profileNuevo", "skillsNuevo", "technologiesNuevo", 22.3, ConstraintViolationException.class
		//			},
		//			/*
		//			 * A:Req 9.1 Create a position, profile in blank
		//			 * C:
		//			 * D:
		//			 */
		//			{
		//				"company1", "titleNuevo", "descriptionNuevo", "2020-02-02", "", "skillsNuevo", "technologiesNuevo", 22.3, ConstraintViolationException.class
		//			},
		//			/*
		//			 * A:Req 9.1 Create a position, skills in blank
		//			 * C:
		//			 * D:
		//			 */
		//			{
		//				"company1", "titleNuevo", "descriptionNuevo", "2020-02-02", "profileNuevo", "", "technologiesNuevo", 22.3, ConstraintViolationException.class
		//			},
		//			/*
		//			 * A:Req 9.1 Create a position, technologies in blank
		//			 * C:
		//			 * D:
		//			 */
		//			{
		//				"company1", "titleNuevo", "descriptionNuevo", "2020-02-02", "profileNuevo", "skillsNuevo", "technologiesNuevo", 22.3, ConstraintViolationException.class
		//			},
		//			/*
		//			 * A:Req 9.1 Create a position, negative salary
		//			 * C:
		//			 * D:
		//			 */
		//			{
		//				"company1", "titleNuevo", "descriptionNuevo", "2020-02-02", "profileNuevo", "skillsNuevo", "technologiesNuevo", -22.3, ConstraintViolationException.class
		//			},

		};

		for (int i = 0; i < testingData.length; i++)
			this.templateCreate((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (String) testingData[i][6], (Double) testingData[i][7],
				(Class<?>) testingData[i][8]);

	}

	protected void templateCreate(final String username, final String title, final String description, final String deadlineString, final String profile, final String skills, final String technologies, final Double salary, final Class<?> expected) {
		Class<?> caught;
		final Position position;
		final Position positionSaved;
		DateFormat formatter;
		java.util.Date deadline;

		this.startTransaction();

		caught = null;
		try {
			super.authenticate(username);

			position = this.positionService.create();

			formatter = new SimpleDateFormat("yyyy-MM-dd");
			deadline = formatter.parse(deadlineString);

			position.setTitle(title);
			position.setDescription(description);
			position.setDeadline(deadline);
			position.setProfile(profile);
			position.setSkills(skills);
			position.setTechnologies(technologies);
			position.setSalary(salary);

			positionSaved = this.positionService.save(position);

			this.positionService.flush();

			Assert.notNull(positionSaved);

			super.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.rollbackTransaction();

		super.checkExceptions(expected, caught);
	}

	@Test
	public void driverEdit() {
		final Object testingData[][] = {
			/*
			 * A:Req 9.1 Create a position, all datas
			 * C:
			 * D:
			 */
			{
				"company1", "position1", "titleNuevo", "descriptionNuevo", "2020-02-02", "profileNuevo", "skillsNuevo", "technologiesNuevo", 22.3, null
			},

			/*
			 * A:Req 9.1 Create a position, title in blank
			 * C:
			 * D:
			 */
			{
				"company1", "position1", "", "descriptionNuevo", "2020-02-02", "profileNuevo", "skillsNuevo", "technologiesNuevo", 22.3, null
			},
		/*
		 * A:Req 9.1 Create a position, description in blank
		 * C:
		 * D:
		 */
		//			{
		//				"company1","position1", "titleNuevo", "", "2020-02-02", "profileNuevo", "skillsNuevo", "technologiesNuevo", 22.3, ConstraintViolationException.class
		//			},
		//			/*
		//			 * A:Req 9.1 Create a position, profile in blank
		//			 * C:
		//			 * D:
		//			 */
		//			{
		//				"company1","position1", "titleNuevo", "descriptionNuevo", "2020-02-02", "", "skillsNuevo", "technologiesNuevo", 22.3, ConstraintViolationException.class
		//			},
		//			/*
		//			 * A:Req 9.1 Create a position, skills in blank
		//			 * C:
		//			 * D:
		//			 */
		//			{
		//				"company1", "position1","titleNuevo", "descriptionNuevo", "2020-02-02", "profileNuevo", "", "technologiesNuevo", 22.3, ConstraintViolationException.class
		//			},
		//			/*
		//			 * A:Req 9.1 Create a position, technologies in blank
		//			 * C:
		//			 * D:
		//			 */
		//			{
		//				"company1", "position1","titleNuevo", "descriptionNuevo", "2020-02-02", "profileNuevo", "skillsNuevo", "technologiesNuevo", 22.3, ConstraintViolationException.class
		//			},
		//			/*
		//			 * A:Req 9.1 Create a position, negative salary
		//			 * C:
		//			 * D:
		//			 */
		//			{
		//				"company1", "position1","titleNuevo", "descriptionNuevo", "2020-02-02", "profileNuevo", "skillsNuevo", "technologiesNuevo", -22.3, ConstraintViolationException.class
		//			},

		};

		for (int i = 0; i < testingData.length; i++)
			this.templateEdit((String) testingData[i][0], super.getEntityId((String) testingData[i][1]), (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (String) testingData[i][6],
				(String) testingData[i][7], (Double) testingData[i][8], (Class<?>) testingData[i][9]);

	}

	protected void templateEdit(final String username, final int positionId, final String title, final String description, final String deadlineString, final String profile, final String skills, final String technologies, final Double salary,
		final Class<?> expected) {
		Class<?> caught;
		final Position position;
		final Position positionSaved;
		DateFormat formatter;
		java.util.Date deadline;

		this.startTransaction();

		caught = null;
		try {
			super.authenticate(username);

			position = this.positionService.findOneToEditDelete(positionId);

			formatter = new SimpleDateFormat("yyyy-MM-dd");
			deadline = formatter.parse(deadlineString);

			position.setTitle(title);
			position.setDescription(description);
			position.setDeadline(deadline);
			position.setProfile(profile);
			position.setSkills(skills);
			position.setTechnologies(technologies);
			position.setSalary(salary);

			positionSaved = this.positionService.save(position);

			this.positionService.flush();

			Assert.notNull(positionSaved);

			super.unauthenticate();
		} catch (final Throwable oops) {
			caught = oops.getClass();
		}

		this.rollbackTransaction();

		super.checkExceptions(expected, caught);
	}

	/*
	 * A: Req 9.2 Delete a problem
	 * C:
	 * D:intentionally blank.there's nothing to check
	 */
	@Test
	public void delete_positive_test() {
		super.authenticate("company1");

		int positionId;
		Position position;

		positionId = super.getEntityId("position4");
		position = this.positionService.findOneToEditDelete(positionId);

		this.positionService.delete(position);

		super.unauthenticate();
	}

	/*
	 * A: Req 9.2 Delete a problem
	 * C:
	 * D:intentionally blank.there's nothing to check
	 */
	@Test(expected = IllegalArgumentException.class)
	public void delete_negative_test() {
		super.authenticate("company1");

		int positionId;
		Position position;

		positionId = super.getEntityId("position4");
		position = this.positionService.findOneToEditDelete(positionId);

		this.positionService.delete(position);

		super.unauthenticate();
	}

	/*
	 * A: Req 9.2 Display a problem
	 * C:
	 * D:intentionally blank.there's nothing to check
	 */
	@Test
	public void display_positive_test() {
		super.authenticate("company1");

		int positionId;

		positionId = super.getEntityId("position4");
		this.positionService.findOneToDisplay(positionId);

		super.unauthenticate();
	}

	/*
	 * A: Req 9.2 Display a problem
	 * C:
	 * D:intentionally blank.there's nothing to check
	 */
	@Test(expected = IllegalArgumentException.class)
	public void display_negative_test() {
		super.authenticate("company1");

		int positionId;

		positionId = super.getEntityId("position4");
		this.positionService.findOneToDisplay(positionId);

		super.unauthenticate();
	}

}
