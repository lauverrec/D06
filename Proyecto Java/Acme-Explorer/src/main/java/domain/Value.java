
package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.Valid;

import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Value extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private Integer	value;


	@Range(min = 0, max = 10)
	public Integer getValue() {
		return this.value;
	}

	public void setValue(int value) {
		this.value = value;
	}


	// Relationships ----------------------------------------------------------
	private Collection<Tag>	tags;


	@Valid
	@ManyToMany
	public Collection<Tag> getTags() {
		return this.tags;
	}

	public void setTags(Collection<Tag> tags) {
		this.tags = tags;
	}

}
