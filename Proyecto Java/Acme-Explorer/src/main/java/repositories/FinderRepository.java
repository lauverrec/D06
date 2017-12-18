
package repositories;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Finder;
import domain.Trip;

@Repository
public interface FinderRepository extends JpaRepository<Finder, Integer> {

	@Query("select t from Trip t where (t.ticker like %?1% or t.title like %?1% or t.description like %?1%)")
	Page<Trip> findByKeyWord(String keyWord, Pageable pageable);

	@Query("select t from Trip t where t.price>=?1")
	Page<Trip> findByLowPrice(Double lowPrice, Pageable pageable);

	@Query("select t from Trip t where t.price<=?1")
	Page<Trip> findByHighPrice(Double highPrice, Pageable pageable);

	@Query("select t from Trip t where t.startDate<=?1")
	Page<Trip> findByInitialDate(Date initialDate, Pageable pageable);

	@Query("select t from Trip t where t.finishDate>=?1")
	Page<Trip> findByFinalDate(Date initialDate, Pageable pageable);
}
