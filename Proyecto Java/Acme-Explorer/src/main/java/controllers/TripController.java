
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

	// Listing ----------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Trip> trips;

		trips = this.tripService.findAllTripsNoAuthenticate();

		result = new ModelAndView("trip/list");
		result.addObject("trips", trips);
		result.addObject("requestURI", "trip/list.do");

		return result;
	}

	@RequestMapping(value = "/category/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int categoryId) {

		ModelAndView result;
		Collection<Trip> trips;

		trips = this.tripService.findByCategory(categoryId);

		result = new ModelAndView("trip/list");
		result.addObject("trips", trips);
		result.addObject("requestURI", "trip/category/list.do");

		return result;
	}

	// Display ---------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int tripId) {
		ModelAndView result;
		Trip trip;
		Collection<Tag> tags;

		trip = this.tripService.findOne(tripId);
		result = new ModelAndView("trip/display");
		result.addObject("trip", trip);
		tags = new ArrayList<Tag>(trip.getTags());
		result.addObject("tags", tags);

		return result;
	}
}
