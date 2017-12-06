
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.AuditRecordRepository;
import domain.AuditRecord;

@Component
@Transactional
public class StringToAuditRecordAuditorConverter implements Converter<String, AuditRecord> {

	@Autowired
	private AuditRecordRepository	auditRecordRepository;


	@Override
	public AuditRecord convert(String text) {
		AuditRecord result;
		int id;
		try {
			id = Integer.valueOf(text);
			result = this.auditRecordRepository.findOne(id);
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}
