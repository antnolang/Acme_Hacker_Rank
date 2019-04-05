
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Problem;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Integer> {

	@Query("select p from Problem p where p.company.id =?1")
	Collection<Problem> findByCompany(int id);

	@Query("select distinct p from Application a join a.problem p where a.position.id=?1 and (a.hacker.id = ?2 or a.status = 'ACCEPTED')")
	Collection<Problem> problemsWithAcceptedApplicationWithOwnApplication(int idPosition, int idHacker);

	@Query("select p from Problem p join p.positions po where po.id=?1")
	Collection<Problem> problemsPosition(int idPosition);
}
