
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.PersonalRecordRepository;
import domain.Curricula;
import domain.PersonalRecord;
import domain.Ranger;

@Service
@Transactional
public class PersonalRecordService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private PersonalRecordRepository	personalRecordRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private RangerService				rangerService;

	@Autowired
	private CurriculaService			curriculaService;


	// Constructors-------------------------------------------------------

	public PersonalRecordService() {
		super();
	}

	// Simple CRUD methods------------------------------------------------

	public PersonalRecord create() {
		PersonalRecord personalRecord;

		personalRecord = new PersonalRecord();

		return personalRecord;
	}

	public Collection<PersonalRecord> findAll() {
		Collection<PersonalRecord> personalRecords;

		personalRecords = this.personalRecordRepository.findAll();
		Assert.notNull(personalRecords);

		return personalRecords;
	}

	public PersonalRecord findOne(final int personalRecordId) {
		Assert.isTrue(personalRecordId != 0);

		PersonalRecord personalRecord;

		personalRecord = this.personalRecordRepository.findOne(personalRecordId);

		return personalRecord;
	}

	public PersonalRecord save(final PersonalRecord personalRecord) {
		Assert.notNull(personalRecord);

		PersonalRecord result;
		Ranger rangerPrincipal;
		final Curricula curriculaPrincipal;

		result = this.personalRecordRepository.save(personalRecord);
		rangerPrincipal = this.rangerService.findByPrincipal();
		curriculaPrincipal = this.curriculaService.findCurriculaFromRanger(rangerPrincipal.getId());
		if (!curriculaPrincipal.getPersonalRecord().equals(personalRecord))
			curriculaPrincipal.setPersonalRecord(personalRecord);

		Assert.notNull(result);

		return result;
	}

}
