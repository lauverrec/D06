
package controllers.explorer;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ExplorerService;
import services.TripService;
import controllers.AbstractController;
import domain.Explorer;
import domain.Trip;

@Controller
@RequestMapping("/trip/explorer")
public class TripExplorerController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private TripService		tripService;

	@Autowired
	private ExplorerService	explorerService;


	// Constructors -----------------------------------------------------------
	public TripExplorerController() {

	}

	// Listing ----------------------------------------------------------------
	@RequestMapping(value = "/list-apply", method = RequestMethod.GET)
	public ModelAndView listApplied() {
		ModelAndView result;
		Collection<Trip> trips;
		Explorer explorer;

		explorer = this.explorerService.findByPrincipal();
		trips = this.tripService.findAllTripsApplyByExplorerId(explorer.getId());

		result = new ModelAndView("trip/list");
		result.addObject("trips", trips);
		result.addObject("apply", true);
		result.addObject("requestURI", "trip/explorer/search.do");

		return result;
	}

	@RequestMapping(value = "/list-not-apply", method = RequestMethod.GET)
	public ModelAndView listNotApplied() {
		ModelAndView result;
		Collection<Trip> trips;
		Explorer explorer;

		explorer = this.explorerService.findByPrincipal();
		trips = this.tripService.findAllTripsNotApplyByExplorerId(explorer.getId());

		result = new ModelAndView("trip/list");
		result.addObject("trips", trips);
		result.addObject("apply", false);
		result.addObject("requestURI", "trip/explorer/search.do");

		return result;
	}

	@RequestMapping(value = "/listTrip", method = RequestMethod.GET)
	public ModelAndView listTripForWritingStory() {
		ModelAndView result;
		Collection<Trip> trips;

		trips = this.tripService.findTripsForStory();

		result = new ModelAndView("trip/liststory");
		result.addObject("trips", trips);
		result.addObject("requestURI", "trip/explorer/search.do");

		return result;
	}

	// Creation ---------------------------------------------------------------

	// Edition ----------------------------------------------------------------

	//Edit
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int tripId) {
		ModelAndView result;
		Trip trip;

		trip = this.tripService.findOneToEdit(tripId);
		Assert.notNull(trip);
		result = this.createEditModelAndView(trip);

		return result;
	}

	/*
	 * //Apply
	 * 
	 * @RequestMapping(value = "/apply", method = RequestMethod.GET)
	 * public ModelAndView apply(@RequestParam final int tripId) {
	 * ModelAndView result;
	 * 
	 * try {
	 * this.eventService.register(eventId);
	 * result = this.listNotRegistered();
	 * result.addObject("message", "event.commit.ok");
	 * } catch (final Throwable oops) {
	 * result = this.listNotRegistered();
	 * result.addObject("message", "event.commit.error");
	 * }
	 * 
	 * return result;
	 * }
	 */

	//Cancel

	//Delete

	//Cancel

	// Search -----------------------------------------------------------------

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView listByKeywordPriceDate(@RequestParam final String keyword, @RequestParam final double lowPrice, @RequestParam final double highPrice, @RequestParam final Date initialDate, @RequestParam final Date finalDate) {
		ModelAndView result;
		Collection<Trip> trips;

		trips = this.tripService.findAllTripsByKeyWordPriceDate(keyword, lowPrice, highPrice, initialDate, finalDate);
		result = new ModelAndView("trip/list");

		result.addObject("trips", trips);
		result.addObject("requestURI", "trip/explorer/search.do");

		return result;
	}
	// Ancillary methods ------------------------------------------------------
	protected ModelAndView createEditModelAndView(final Trip trip) {
		ModelAndView result;

		result = this.createEditModelAndView(trip, null);
		return result;
	}

	private ModelAndView createEditModelAndView(final Trip trip, final String message) {
		ModelAndView result;

		result = new ModelAndView("trip/edit");
		result.addObject("trip", trip);
		result.addObject("message", message);

		return result;
	}
}
