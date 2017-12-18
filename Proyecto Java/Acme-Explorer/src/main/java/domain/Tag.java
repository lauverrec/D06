
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.Valid;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Tag extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private String	name;


	@NotBlank
	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}


	// Relationships ----------------------------------------------------------

	private Collection<Value>	values;
	private Collection<Trip>	trips;


	@Valid
	@ManyToMany(mappedBy = "tags")
	public Collection<Value> getValues() {
		return this.values;
	}

	public void setValues(Collection<Value> values) {
		this.values = values;
	}

	@Valid
	@ManyToMany(mappedBy = "tags")
	public Collection<Trip> getTrips() {
		return this.trips;
	}

	public void setTrips(Collection<Trip> trips) {
		this.trips = trips;
	}

}
