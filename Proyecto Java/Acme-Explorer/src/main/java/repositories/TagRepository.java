
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Tag;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {

	@Query("select distinct(c.tags) from Trip c")
	Collection<Tag> findTagWithTrip();

	@Query("select t from Tag t where t.value.value=2")
	Collection<Tag> findAllTagUnique();

	@Query("select t from Tag t where t.name=?1")
	Collection<Tag> findAllTagByName(String name);
}
