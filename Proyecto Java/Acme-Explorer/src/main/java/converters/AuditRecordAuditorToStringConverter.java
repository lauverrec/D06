
package converters;

import javax.transaction.Transactional;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import domain.AuditRecord;

@Component
@Transactional
public class AuditRecordAuditorToStringConverter implements Converter<AuditRecord, String> {

	@Override
	public String convert(AuditRecord auditRecord) {
		String result;

		if (auditRecord == null)
			result = null;
		else
			result = String.valueOf(auditRecord.getId());
		return result;
	}

}
