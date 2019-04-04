
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

}
