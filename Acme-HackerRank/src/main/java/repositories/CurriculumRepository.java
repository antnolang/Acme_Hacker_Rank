
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Curriculum;

@Repository
public interface CurriculumRepository extends JpaRepository<Curriculum, Integer> {

	// Req 18.1.1
	@Query("select min(1 * (select count(c) from Curriculum c where c.hacker.id = h.id)), max(1 * (select count(c) from Curriculum c where c.hacker.id = h.id)), avg(1 * (select count(c) from Curriculum c where c.hacker.id = h.id)), stddev(1 * (select count(c) from Curriculum c where c.hacker.id = h.id)) from Hacker h")
	Double[] findDataNumberCurriculumPerHacker();

	@Query("select c from Curriculum c where c.hacker.id = ?1")
	Collection<Curriculum> findAllByHacker(int hackerId);

	@Query("select c from Curriculum c where c.hacker.id=?1 and c.isOriginal=true")
	Collection<Curriculum> originalCurricula(int hackerId);
}
