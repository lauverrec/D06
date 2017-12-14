
package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Value extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private int	value;


	@Range(min = 0, max = 10)
	public int getValue() {
		return this.value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	// Relationships ----------------------------------------------------------

}
