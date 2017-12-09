
package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SurvivalClassRepository;
import domain.Explorer;
import domain.GPS;
import domain.Manager;
import domain.SurvivalClass;
import domain.Trip;

@Service
@Transactional
public class SurvivalClassService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private SurvivalClassRepository	survivalClassRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ManagerService			managerService;

	@Autowired
	private TripService				tripService;

	@Autowired
	private ExplorerService			explorerService;


	// Constructors-------------------------------------------------------

	public SurvivalClassService() {

		super();
	}

	// Simple CRUD methods------------------------------------------------

	public SurvivalClass create() {

		this.managerService.checkPrincipal();

		Manager manager;
		Collection<Explorer> explorers;
		Date organisedMoment;
		Trip trip;
		GPS location;
		String title;
		String description;

		SurvivalClass result;

		manager = new Manager();
		explorers = new ArrayList<>();
		organisedMoment = new Date();
		trip = new Trip();
		location = new GPS();
		title = new String();
		description = new String();

		result = new SurvivalClass();
		result.setManager(manager);
		result.setExplorers(explorers);
		result.setOrganisedMoment(organisedMoment);
		result.setTrip(trip);
		result.setLocation(location);
		result.setTitle(title);
		result.setDescription(description);

		return result;
	}
	public Collection<SurvivalClass> findAll() {

		this.managerService.checkPrincipal();

		Collection<SurvivalClass> result;

		result = this.survivalClassRepository.findAll();

		Assert.notNull(result);

		return result;
	}

	public SurvivalClass findOne(final int survivalClassId) {

		SurvivalClass result;

		result = this.survivalClassRepository.findOne(survivalClassId);

		return result;
	}

	public SurvivalClass save(final SurvivalClass survivalClass) {

		this.managerService.checkPrincipal();

		Assert.notNull(survivalClass);

		SurvivalClass result;

		if (survivalClass.getId() == 0) {
			final Date organisedMoment = new Date();
			survivalClass.setOrganisedMoment(organisedMoment);
		}

		result = this.survivalClassRepository.save(survivalClass);

		Assert.notNull(result);

		return result;
	}
	public void delete(final SurvivalClass survivalClass) {

		this.managerService.checkPrincipal();

		Assert.notNull(survivalClass);
		Assert.isTrue(survivalClass.getId() != 0);

		this.survivalClassRepository.delete(survivalClass);

	}

	//Other bussines methods--------------------------------------------------------

	public Collection<SurvivalClass> findSurvivalClassByManager() {
		Collection<SurvivalClass> classes;
		Trip trip;

		trip = this.tripService.findAll().iterator().next();
		classes = this.survivalClassRepository.findSurvivalClassByManager(trip.getId());

		return classes;
	}

	public Trip findByPrincipalSurvivalClassTrip(final SurvivalClass survivalClass) {

		Trip result;
		result = this.survivalClassRepository.findTripBySurvivalClass(survivalClass.getId());
		return result;

	}

	public Trip findByPrincipalSurvivalClassTripId(final int survivalClassId) {

		Trip result;
		result = this.survivalClassRepository.findTripBySurvivalClass(survivalClassId);
		return result;

	}

	public Collection<SurvivalClass> findAllByTripIdEnrol(final int tripId, final int explorerId) {
		Collection<SurvivalClass> result;

		result = this.survivalClassRepository.findAllByTripIdEnrol(tripId, explorerId);

		return result;
	}

	public Collection<SurvivalClass> findAllByTripIdNotEnrol(final int tripId, final int explorerId) {
		Collection<SurvivalClass> result;

		result = this.survivalClassRepository.findAllByTripIdNotEnrol(tripId, explorerId);

		return result;
	}

	public void notEnrolExplorerPrincipal(final int survivalClassId) {
		Assert.notNull(survivalClassId);
		Explorer explorerPrincipal;
		SurvivalClass survivalClass;

		explorerPrincipal = this.explorerService.findByPrincipal();
		survivalClass = this.findOne(survivalClassId);
		Assert.notNull(survivalClass);
		Assert.isTrue(survivalClass.getExplorers().contains(explorerPrincipal));
		survivalClass.getExplorers().remove(explorerPrincipal);

	}

	public void enrolExplorerPrincipal(final int survivalClassId) {
		Assert.notNull(survivalClassId);
		Explorer explorerPrincipal;
		SurvivalClass survivalClass;

		explorerPrincipal = this.explorerService.findByPrincipal();
		survivalClass = this.findOne(survivalClassId);
		Assert.notNull(survivalClass);
		Assert.isTrue(!survivalClass.getExplorers().contains(explorerPrincipal));
		survivalClass.getExplorers().add(explorerPrincipal);

	}

}
