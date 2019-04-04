
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Integer> {

	@Query("select a from Application a where a.problem.id=?1 and a.hacker.id=?2")
	Collection<Application> findApplicationsByProblemHacker(int idProblem, int idHacker);

	// Req 11.2.2
	@Query("select avg(1 * (select count(a) from Application a where a.hacker.id = h.id)), min(1 * (select count(a) from Application a where a.hacker.id = h.id)), max(1 * (select count(a) from Application a where a.hacker.id = h.id)), stddev(1 * (select count(a) from Application a where a.hacker.id = h.id)) from Hacker h")
	Double[] findDataNumberApplicationPerHacker();

}
