
package controllers.manager;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.LegalTextService;
import services.ManagerService;
import services.RangerService;
import services.TagService;
import services.TripService;
import controllers.AbstractController;
import domain.Manager;
import domain.Ranger;
import domain.Tag;
import domain.Trip;

@Controller
@RequestMapping("/trip/manager_")
public class TripManagerController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private TripService			tripService;
	@Autowired
	private ManagerService		managerService;
	@Autowired
	private RangerService		rangerService;
	@Autowired
	private LegalTextService	legalTextService;
	@Autowired
	private TagService			tagService;


	// Constructors -----------------------------------------------------------
	public TripManagerController() {

	}

	// Listing ----------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Trip> trips;

		trips = this.tripService.findAll();

		result = new ModelAndView("trip/list");
		result.addObject("trips", trips);
		result.addObject("requestURI", "trip/manager_/list.do");

		return result;
	}

	// Creation ---------------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Trip trip;
		Manager manager;
		manager = this.managerService.findByPrincipal();
		trip = this.tripService.create(manager);
		result = this.createEditModelAndView(trip);

		return result;
	}
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

	//Save
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Trip trip, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(trip);
		else
			try {
				this.tripService.save(trip);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(trip, "trip.commit.error");
			}
		return result;
	}

	//Delete
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(final Trip trip, final BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(trip);
		else
			try {
				this.tripService.delete(trip);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(trip, "trip.commit.error");
			}
		return result;
	}

	//Display

	// Ancillary methods ------------------------------------------------------
	protected ModelAndView createEditModelAndView(Trip trip) {
		ModelAndView result;

		result = this.createEditModelAndView(trip, null);
		return result;
	}

	private ModelAndView createEditModelAndView(Trip trip, String message) {
		ModelAndView result;
		Collection<Ranger> rangers;
		//Collection<LegalText> legalTexts;
		//legalTexts = this.legalTextService.findAll();
		rangers = this.rangerService.findAll();
		Collection<Tag> tags;
		tags = new ArrayList<Tag>(this.tagService.findAll());
		result = new ModelAndView("trip/edit");
		result.addObject("trip", trip);
		result.addObject("tags", tags);
		result.addObject("message", message);
		result.addObject("rangers", rangers);
		//result.addObject("legalTexts", legalTexts);
		return result;
	}
}
