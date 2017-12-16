
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
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

	private Value	value;


	@Valid
	@ManyToOne(optional = true, cascade = CascadeType.ALL)
	//@NotNull
	public Value getValue() {
		return this.value;
	}

	public void setValue(Value value) {
		this.value = value;
	}

}
