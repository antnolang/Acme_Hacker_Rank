
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
import domain.Problem;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class ProblemServiceTest extends AbstractTest {

	// Service under testing -----------------------------------
	@Autowired
	private ProblemService	problemService;

	// Other services ------------------------------------------
	@Autowired
	private CompanyService	companyService;


	// Suite test ---------------------------------------------

	@Test
	public void driverCreate() {
		final Object testingData[][] = {
			/*
			 * A:Req 9.2 Create a problem, all datas
			 * C:
			 * D: 6.25% (1/16) we test 1 of the 16 possible combinations that can take in total.
			 */
			{
				"company1", "titleNuevoProblem1", "statementNuevoProblem1", "hintNuevoProblem1", "attachmentsNuevoProblem1", null
			},

			/*
			 * A:Req 9.2 Create a problem, without hint (hint is optional)
			 * C:
			 * D: 6.25% ((1/16) we test 1 of the 16 possible combinations that can take in total.
			 */
			{
				"company1", "titleNuevoProblem2", "statementNuevoProblem2", "", "attachmentsNuevoProblem2", null
			},
			/*
			 * A: Req 9.2 Create a problem
			 * B: Title in blank
			 * C:
			 * D: 6.25% (1/16) we test 1 of the 16 possible combinations that can take in total.
			 */
			{
				"company1", "", "statementNuevoProblem3", "hintNuevoProblem3", "attachmentsNuevoProblem3", ConstraintViolationException.class
			},
			/*
			 * A: Req 9.2 Create a problem
			 * B: Statement in blank
			 * C:
			 * D: 6.25% (1/16) we test 1 of the 16 possible combinations that can take in total.
			 */
			{
				"company1", "titleNuevoProblem4", "", "hintNuevoProblem4", "attachmentsNuevoProblem4", ConstraintViolationException.class
			},

		};

		for (int i = 0; i < testingData.length; i++)
			this.templateCreate((String) testingData[i][0], (String) testingData[i][1], (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (Class<?>) testingData[i][5]);

	}

	protected void templateCreate(final String username, final String title, final String statement, final String hint, final String attachments, final Class<?> expected) {
		Class<?> caught;
		final Problem problem;
		final Problem problemSaved;

		this.startTransaction();

		caught = null;
		try {
			super.authenticate(username);

			problem = this.problemService.create();

			problem.setTitle(title);
			problem.setAttachments(attachments);
			problem.setHint(hint);
			problem.setStatement(statement);

			problemSaved = this.problemService.save(problem);

			this.problemService.flush();

			Assert.notNull(problemSaved);

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
			 * A:Req 9.2 Edit a problem, all datas
			 * C:
			 * D: 6.25% (1/16) we test 1 of the 16 possible combinations that can take in total.
			 */
			{
				"company1", "problem4", "titleEditado", "statementEditado", "hintEditado", "attachmentsEditado", null
			},

			/*
			 * A:Req 9.2 Edit a problem, without hint (hint is optional)
			 * C:
			 * D: 6.25% ((1/16) we test 1 of the 16 possible combinations that can take in total.
			 */
			{
				"company1", "problem4", "titleEditado", "statementEditado", "", "attachmentsEditado", null
			},
			/*
			 * A: Req 9.2 Edit a problem
			 * B: Title in blank
			 * C:
			 * D: 6.25% (1/16) we test 1 of the 16 possible combinations that can take in total.
			 */
			{
				"company1", "problem4", "", "statementEditado", "hintEditado", "attachmentsEditado", ConstraintViolationException.class
			},
			/*
			 * A: Req 9.2 Edit a problem
			 * B: Statement in blank
			 * C:
			 * D: 6.25% (1/16) we test 1 of the 16 possible combinations that can take in total.
			 */
			{
				"company1", "problem4", "titleEditado", "", "hintEditado", "attachmentsEditado", ConstraintViolationException.class
			},
			/*
			 * A: Req 9.2 Edit a problem
			 * B: Edit a problem in final mode
			 * C:
			 * D:intentionally blank.there's nothing to check
			 */
			{
				"company1", "problem1", "titleEditado", "statementEditado", "hintEditado", "attachmentsEditado", IllegalArgumentException.class
			},
			/*
			 * A: Req 9.2 Edit a problem
			 * B: Edit a problem from another user
			 * C:
			 * D:intentionally blank.there's nothing to check
			 */
			{
				"company2", "problem4", "titleEditado", "statementEditado", "hintNuevoProblem4", "attachmentsEditado", IllegalArgumentException.class
			},

		};

		for (int i = 0; i < testingData.length; i++)
			this.templateEdit((String) testingData[i][0], super.getEntityId((String) testingData[i][1]), (String) testingData[i][2], (String) testingData[i][3], (String) testingData[i][4], (String) testingData[i][5], (Class<?>) testingData[i][6]);

	}

	protected void templateEdit(final String username, final Integer problemId, final String title, final String statement, final String hint, final String attachments, final Class<?> expected) {
		Class<?> caught;
		final Problem problem;
		final Problem problemSaved;

		this.startTransaction();

		caught = null;
		try {
			super.authenticate(username);

			problem = this.problemService.findOneToEditDelete(problemId);

			problem.setTitle(title);
			problem.setAttachments(attachments);
			problem.setHint(hint);
			problem.setStatement(statement);

			problemSaved = this.problemService.save(problem);

			this.problemService.flush();

			Assert.notNull(problemSaved);

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

		int problemId;
		Problem problem;

		problemId = super.getEntityId("problem4");
		problem = this.problemService.findOneToEditDelete(problemId);

		this.problemService.delete(problem);

		super.unauthenticate();
	}

	/*
	 * A: Req 9.2 Delete a problem
	 * B: Delete a problem from another user
	 * C:
	 * D:intentionally blank.there's nothing to check
	 */
	@Test(expected = IllegalArgumentException.class)
	public void delete_negative_test() {
		super.authenticate("company2");

		int problemId;
		Problem problem;

		problemId = super.getEntityId("problem4");
		problem = this.problemService.findOneToEditDelete(problemId);

		this.problemService.delete(problem);

		super.unauthenticate();
	}

	/*
	 * A: Req 9.2 Delete a problem
	 * B: Delete a problem in final mode
	 * C:
	 * D:intentionally blank.there's nothing to check
	 */
	@Test(expected = IllegalArgumentException.class)
	public void delete_negative1_test() {
		super.authenticate("company1");

		int problemId;
		Problem problem;

		problemId = super.getEntityId("problem1");
		problem = this.problemService.findOneToEditDelete(problemId);

		this.problemService.delete(problem);

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

		int problemId;

		problemId = super.getEntityId("problem1");
		this.problemService.findOneToPrincipal(problemId);

		super.unauthenticate();
	}

	/*
	 * A: Req 9.2 Display a problem
	 * B: Display a from from another user
	 * C:
	 * D:intentionally blank.there's nothing to check
	 */
	@Test(expected = IllegalArgumentException.class)
	public void display_negative_test() {
		super.authenticate("company2");

		int problemId;

		problemId = super.getEntityId("problem1");
		this.problemService.findOneToPrincipal(problemId);

		super.unauthenticate();
	}
}
