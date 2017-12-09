
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.SurvivalClass;
import domain.Trip;

@Repository
public interface SurvivalClassRepository extends JpaRepository<SurvivalClass, Integer> {

	@Query("select s from SurvivalClass s where s.trip.id=?1 and s.trip.manager=s.manager")
	Collection<SurvivalClass> findSurvivalClassByManager(int tripId);

	@Query("select s.trip from SurvivalClass s where s.id=?1 and s.trip.manager=s.manager")
	Trip findTripBySurvivalClass(int survivalClassId);

	@Query("select s from SurvivalClass s join s.explorers e where s.trip.id=?1 and e.id=?2")
	Collection<SurvivalClass> findAllByTripIdEnrol(int tripId, int explorerId);

	@Query("select s from SurvivalClass s join s.explorers e where s.trip.id=?1 and e.id!=?2")
	Collection<SurvivalClass> findAllByTripIdNotEnrol(int tripId, int explorerId);
}
