
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Sponsor extends Actor {

	// Attributes -------------------------------------------------------------

	// Relationships ----------------------------------------------------------

	private Collection<Sponsorship>	sponsorships;


	@Valid
	@NotNull
	@OneToMany(mappedBy = "sponsor")
	public Collection<Sponsorship> getSponsorships() {
		return this.sponsorships;
	}

	public void setSponsorships(Collection<Sponsorship> sponsorships) {
		this.sponsorships = sponsorships;
	}
}
