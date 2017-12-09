
package controllers.explorer;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ApplicationForService;
import services.ExplorerService;
import services.TripService;
import domain.ApplicationFor;
import domain.Explorer;
import domain.Trip;

@Controller
@RequestMapping("/applicationFor/explorer")
public class ApplicationForExplorerController {

	// Services ---------------------------------------------------------------
	@Autowired
	private TripService				tripService;
	@Autowired
	private ApplicationForService	applicationForService;
	@Autowired
	private ExplorerService			explorerSerice;


	//public Trip						tripAsociado;

	// Constructors -----------------------------------------------------------
	public ApplicationForExplorerController() {

	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int tripId) {
		ModelAndView result;
		Trip trip;
		ApplicationFor applicationFor;

		trip = this.tripService.findOne(tripId);
		Assert.notNull(trip);
		applicationFor = this.applicationForService.create(trip);
		result = this.createEditModelAndView(applicationFor);
		result.addObject("trip", trip);

		//this.tripAsociado = trip;
		//trip.getApplicationsFor().add(applicationFor);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid ApplicationFor applicationFor, BindingResult binding) {
		ModelAndView result;
		//Collection<ApplicationFor> applicationsFor;	
		if (binding.hasErrors())
			result = this.createEditModelAndView(applicationFor);
		else
			try {
				this.applicationForService.save(applicationFor);
				Explorer explorer;
				explorer = this.explorerSerice.findByPrincipal();
				explorer.getApplicationsFor().add(applicationFor);

				result = new ModelAndView("redirect:../../trip/explorer/list-apply.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(applicationFor, "applicationfor.commit.error");
			}
		return result;
	}

	protected ModelAndView createEditModelAndView(final ApplicationFor applicationFor) {
		ModelAndView result;

		result = this.createEditModelAndView(applicationFor, null);
		return result;
	}

	private ModelAndView createEditModelAndView(final ApplicationFor applicationFor, final String message) {
		ModelAndView result;

		result = new ModelAndView("applicationFor/edit");
		result.addObject("applicationFor", applicationFor);
		result.addObject("message", message);

		return result;
	}

}
