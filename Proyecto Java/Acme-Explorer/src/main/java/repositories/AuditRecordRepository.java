
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.AuditRecord;
import domain.Trip;

@Repository
public interface AuditRecordRepository extends JpaRepository<AuditRecord, Integer> {

	@Query("select t from Trip t join t.auditRecords a where a.id=?1")
	Trip findTripsGivingAnAuditRecord(int auditRecodId);

	@Query("select a from AuditRecord a where a.auditor.id=?1")
	Collection<AuditRecord> findByAuditorId(int auditorId);

}
