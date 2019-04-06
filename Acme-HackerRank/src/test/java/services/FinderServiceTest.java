
package services;

import javax.transaction.Transactional;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import repositories.FinderRepository;
import repositories.PositionRepository;
import utilities.AbstractTest;
import domain.Finder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/junit.xml"
})
@Transactional
public class FinderServiceTest extends AbstractTest {

	// Service under testing --------------------------------------------------

	@Autowired
	private FinderService		finderService;

	// Other services and repositories ----------------------------------------

	@Autowired
	private FinderRepository	finderRepository;

	@Autowired
	private PositionRepository	positionRepository;


	// Tests ------------------------------------------------------------------

	//	 TODO: Tests funcionales Finder
	//		/*
	//		 * A: An actor who is authenticated as a hacker must be able to: Manage 
	//		 * his or her finder, which involves UPDATING THE SEARCH CRITERIA, 
	//		 * listing its contents, and clearing it
	//		 * 
	//		 * B: Positive test
	//		 * 
	//		 * C: TODO: Sentence coverage
	//		 * 
	//		 * D: TODO: Data coverage
	//		 */
	//		@Test()
	//		public void finderSearchTest() {
	//			Collection<Position> results;
	//			Finder finder, saved;
	//			Position position;
	//			int finderId, positionId;
	//			
	//			super.authenticate("hacker9");
	//	
	//			finderId = super.getEntityId("finder9");
	//			positionId = super.getEntityId("position4");
	//			
	//			position = this.positionRepository.findOne(positionId);
	//			finder = this.finderRepository.findOne(finderId);
	//			finder = this.cloneFinder(finder);
	//			
	//			finder.setKeyword("56");
	//			finder.setMaximumDeadline(LocalDate.parse("2019/11/08").toDate());
	//			saved = this.finderService.save(finder);
	//			
	//			results = saved.getPositions();
	//			
	//			super.unauthenticate();
	//	
	//			Assert.isTrue(results.size() == 1);
	//			Assert.isTrue(results.contains(position));
	//		}
	//	
	//		/*
	//		 * A: An actor who is authenticated as a hacker must be able to: Manage 
	//		 * his or her finder, which involves UPDATING THE SEARCH CRITERIA, 
	//		 * listing its contents, and clearing it
	//		 * 
	//		 * B: The finder can only be used by its owner.
	//		 * 
	//		 * C: TODO: Sentence coverage
	//		 * 
	//		 * D: TODO: Data coverage
	//		 */
	//		@Test(expected = IllegalArgumentException.class)
	//		public void finderSearchNegativeTest() {
	//			Collection<Position> results;
	//			Finder finder, saved;
	//			Position position;
	//			int finderId, positionId;
	//			
	//			super.authenticate("hacker9");
	//	
	//			finderId = super.getEntityId("finder8");
	//			positionId = super.getEntityId("position4");
	//			
	//			position = this.positionRepository.findOne(positionId);
	//			finder = this.finderRepository.findOne(finderId);
	//			finder = this.cloneFinder(finder);
	//			
	//			finder.setKeyword("56");
	//			finder.setMaximumDeadline(LocalDate.parse("2019/11/08").toDate());
	//			saved = this.finderService.save(finder);
	//			
	//			results = saved.getPositions();
	//			
	//			super.unauthenticate();
	//	
	//			Assert.isTrue(results.size() == 1);
	//			Assert.isTrue(results.contains(position));
	//		}
	//		
	//		/*
	//		 * A: An actor who is authenticated as a hacker must be able to: Manage 
	//		 * his or her finder, which involves updating the search criteria, 
	//		 * listing its contents, and CLEARING IT.
	//		 * 
	//		 * B: Positive test
	//		 * 
	//		 * C: TODO: Sentence coverage
	//		 * 
	//		 * D: TODO: Data coverage
	//		 */
	//		@Test()
	//		public void finderClearTest() {
	//			final Collection<Position> results;
	//			Date date;
	//			Finder finder;
	//			final Finder saved;
	//			Position position;
	//			int finderId, positionId;
	//			
	//			super.authenticate("hacker9");
	//	
	//			finderId = super.getEntityId("finder9");
	//			positionId = super.getEntityId("position4");
	//			
	//			position = this.positionRepository.findOne(positionId);
	//			finder = this.finderRepository.findOne(finderId);
	//			
	//			this.finderService.clear(finder);
	//			date = new Date();
	//			
	//			super.unauthenticate();
	//	
	//			Assert.isTrue(finder.getDeadline() == null);
	//			Assert.isTrue(finder.getKeyword() == "");
	//			Assert.isTrue(finder.getMaximumDeadline() == null);
	//			Assert.isTrue(finder.getMinimumSalary() == null);
	//			Assert.isTrue(finder.getPositions().size() == 3);
	//			Assert.isTrue(date.getTime() - finder.getUpdatedMoment().getTime() <= 1000);
	//		}
	//		
	//		/*
	//		 * A: An actor who is authenticated as a hacker must be able to: Manage 
	//		 * his or her finder, which involves updating the search criteria, 
	//		 * listing its contents, and CLEARING IT.
	//		 * 
	//		 * B: The finder can only be used by its owner.
	//		 * 
	//		 * C: TODO: Sentence coverage
	//		 * 
	//		 * D: TODO: Data coverage
	//		 */
	//		@Test()
	//		public void finderClearNegativeTest() {
	//			final Collection<Position> results;
	//			Date date;
	//			Finder finder;
	//			final Finder saved;
	//			Position position;
	//			int finderId, positionId;
	//			
	//			super.authenticate("hacker9");
	//	
	//			finderId = super.getEntityId("finder8");
	//			positionId = super.getEntityId("position4");
	//			
	//			position = this.positionRepository.findOne(positionId);
	//			finder = this.finderRepository.findOne(finderId);
	//			
	//			this.finderService.clear(finder);
	//			date = new Date();
	//			
	//			super.unauthenticate();
	//	
	//			Assert.isTrue(finder.getDeadline() == null);
	//			Assert.isTrue(finder.getKeyword() == "");
	//			Assert.isTrue(finder.getMaximumDeadline() == null);
	//			Assert.isTrue(finder.getMinimumSalary() == null);
	//			Assert.isTrue(finder.getPositions().size() == 3);
	//			Assert.isTrue(date.getTime() - finder.getUpdatedMoment().getTime() <= 1000);
	//		}

	// Ancillary methods --------------------------------------------------

	private Finder cloneFinder(final Finder finder) {
		Finder result;

		result = new Finder();
		result.setDeadline(finder.getDeadline());
		result.setHacker(finder.getHacker());
		result.setId(finder.getId());
		result.setKeyword(finder.getKeyword());
		result.setMaximumDeadline(finder.getMaximumDeadline());
		result.setMinimumSalary(finder.getMinimumSalary());
		result.setPositions(finder.getPositions());
		result.setUpdatedMoment(finder.getUpdatedMoment());
		result.setVersion(finder.getVersion());

		return result;
	}

}
