
package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.TripService;
import domain.AuditRecord;
import domain.Note;
import domain.Stage;
import domain.Tag;
import domain.Trip;

@Controller
@RequestMapping("/trip")
public class TripController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private TripService	tripService;


	//@Autowired
	//private ExplorerService	explorerService;

	// Constructors -----------------------------------------------------------
	public TripController() {

	}

	// Search -----------------------------------------------------------------

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView listByKeyword(@RequestParam final String keyword) {
		ModelAndView result;
		Collection<Trip> trips;

		trips = this.tripService.findAllTripsByKeyWord(keyword);
		result = new ModelAndView("trip/list");

		result.addObject("trips", trips);
		result.addObject("requestURI", "trip/search.do");

		return result;
	}

	// Listing ----------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Trip> trips;
		double price;

		trips = this.tripService.findAllTripsNoAuthenticate();
		price = this.tripService.setPriceTrip(trips);

		result = new ModelAndView("trip/list");
		result.addObject("trips", trips);
		result.addObject("price", price);
		result.addObject("requestURI", "trip/search.do");

		return result;
	}

	@RequestMapping(value = "/category/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam final int categoryId) {

		ModelAndView result;
		Collection<Trip> trips;

		trips = this.tripService.findByCategory(categoryId);

		result = new ModelAndView("trip/list");
		result.addObject("trips", trips);
		result.addObject("requestURI", "trip/search.do");

		return result;
	}

	// Display ---------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int tripId) {
		ModelAndView result;
		Trip trip;
		Collection<Tag> tags;
		//Collection<String> requirements;
		Collection<Stage> stages;
		Collection<AuditRecord> auditRecords;
		Collection<Note> notes;

		trip = this.tripService.findOne(tripId);
		notes = new ArrayList<Note>(trip.getNotes());
		tags = new ArrayList<Tag>(trip.getTags());
		stages = new ArrayList<Stage>(trip.getStages());
		auditRecords = new ArrayList<AuditRecord>(trip.getAuditRecords());

		result = new ModelAndView("trip/display");
		result.addObject("trip", trip);
		result.addObject("tags", tags);
		//result.addObject("requirements", requirements);
		result.addObject("stages", stages);
		result.addObject("auditRecords", auditRecords);
		result.addObject("notes", notes);

		return result;
	}
}
