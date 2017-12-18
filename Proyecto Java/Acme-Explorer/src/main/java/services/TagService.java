
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.TagRepository;
import domain.Tag;

@Service
@Transactional
public class TagService {

	// Managed repository -----------------------------------------------------
	@Autowired
	private TagRepository	tagRepository;


	// Supporting services ----------------------------------------------------
	//@Autowired
	//private TripService		tripService;

	// Constructors------------------------------------------------------------
	public TagService() {
		super();
	}

	// Simple CRUD methods-----------------------------------------------------
	public Tag create() {
		Tag result;
		result = new Tag();
		return result;
	}

	public Collection<Tag> findAll() {
		Collection<Tag> result;

		result = this.tagRepository.findAll();
		Assert.notNull(result);

		return result;
	}

	public Tag findOne(final int tagId) {
		Assert.isTrue(tagId != 0);

		Tag result;
		result = this.tagRepository.findOne(tagId);
		Assert.notNull(result);

		return result;
	}

	public Tag save(final Tag tag) {
		Assert.notNull(tag);

		Tag result;
		result = this.tagRepository.save(tag);
		Assert.notNull(result);

		return result;
	}

	public void delete(final Tag tag) {
		//String name;
		//Collection<Tag> tags;
		//Collection<Trip> trips;

		//Si esa tag no tiene ningun valor asignado en su lista de valores
		//quiere decir que no esta asignada a ninguna trip y se puede eliminar.
		Assert.isTrue(tag.getValues().size() == 0);

		this.tagRepository.delete(tag);
	}

	public Collection<Tag> findAllTagByTripId(int tripId) {
		Collection<Tag> result;
		result = new ArrayList<Tag>(this.tagRepository.findAllTagByTripId(tripId));
		return result;
	}
	/*
	 * public Collection<Tag> findAllTagUnique() {
	 * Collection<Tag> result;
	 * result = new ArrayList<>(this.tagRepository.findAllTagUnique());
	 * return result;
	 * }
	 */

	/*
	 * public Collection<Tag> findAllTagByName(String name) {
	 * Collection<Tag> result;
	 * result = new ArrayList<>(this.tagRepository.findAllTagByName(name));
	 * return result;
	 * }
	 */
}
