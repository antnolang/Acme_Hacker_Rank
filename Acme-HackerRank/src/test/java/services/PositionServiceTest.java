
package services;

import java.util.ArrayList;
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

	@Test
	public void test_findPositionsBestWorstSalary() {

		final List<Position> positions = new ArrayList<>(this.positionService.findPositionsBestWorstSalary());

		Assert.notNull(positions);
		Assert.isTrue(positions.size() == 2);

	}

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

}
