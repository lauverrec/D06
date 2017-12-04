
package converters;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import repositories.SponsorshipRepository;
import domain.Sponsorship;

@Component
@Transactional
public class StringToSponsorshipSponsorConverter implements Converter<String, Sponsorship> {

	@Autowired
	SponsorshipRepository	sponsorshipRepository;


	// method----------------------------------------------------------------

	@Override
	public Sponsorship convert(String text) {
		Sponsorship result;
		int id;
		try {
			id = Integer.valueOf(text);
			result = this.sponsorshipRepository.findOne(id);
		} catch (final Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
