
package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Finder extends DomainEntity {

	// Attributes -------------------------------------------------------------
	private String	keyWord;
	private double	lowPrice;
	private double	highPrice;
	private Date	initialDate;
	private Date	finalDate;
	private Date	startCacheTime;


	public String getKeyWord() {
		return this.keyWord;
	}

	public void setKeyWord(final String keyWord) {
		this.keyWord = keyWord;
	}

	public double getLowPrice() {
		return this.lowPrice;
	}

	public void setLowPrice(final double lowPrice) {
		this.lowPrice = lowPrice;
	}

	public double getHighPrice() {
		return this.highPrice;
	}

	public void setHighPrice(final double highPrice) {
		this.highPrice = highPrice;
	}

	@Temporal(TemporalType.DATE)
	public Date getInitialDate() {
		return this.initialDate;
	}

	public void setInitialDate(final Date initialDate) {
		this.initialDate = initialDate;
	}

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	public Date getFinalDate() {
		return this.finalDate;
	}

	public void setFinalDate(final Date finalDate) {
		this.finalDate = finalDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	public Date getStartCacheTime() {
		return this.startCacheTime;
	}

	public void setStartCacheTime(final Date startCacheTime) {
		this.startCacheTime = startCacheTime;
	}


	// Relationships ----------------------------------------------------------

	private Explorer			explorer;
	private Collection<Trip>	trips;


	@NotNull
	@Valid
	@OneToOne(optional = false, mappedBy = "finder")
	public Explorer getExplorer() {
		return this.explorer;
	}

	public void setExplorer(final Explorer explorer) {
		this.explorer = explorer;
	}

	@NotNull
	@Valid
	@ManyToMany
	public Collection<Trip> getTrips() {
		return this.trips;
	}

	public void setTrips(final Collection<Trip> trips) {
		this.trips = trips;
	}
}
