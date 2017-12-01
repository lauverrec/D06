/* CurriculumToStringConverter.java
 *
 * Copyright (C) 2015 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Curriculum;

@Component
@Transactional
public class CurriculumToStringConverter implements Converter<Curriculum, String> {
	
	@Override
	public String convert(Curriculum curriculum) {
		String result;

		if (curriculum == null)
			result = null;
		else
			result = String.valueOf(curriculum.getId());

		return result;
	}

}
