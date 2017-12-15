
package controllers.manager;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ApplicationForService;
import services.ManagerService;
import services.TripService;
import controllers.AbstractController;
import domain.ApplicationFor;
import domain.Manager;

@Controller
@RequestMapping("/applicationFor/manager")
public class ApplicationForManagerController extends AbstractController {

	// Services ---------------------------------------------------------------
	@Autowired
	private TripService				tripService;

	@Autowired
	private ApplicationForService	applicationForService;

	@Autowired
	private ManagerService			managerService;


	// Constructors -----------------------------------------------------------
	public ApplicationForManagerController() {

	}

	// Listing ----------------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;

		Collection<ApplicationFor> applicationFor;
		Manager manager;
		Date date = new Date();
		manager = this.managerService.findByPrincipal();
		applicationFor = new ArrayList<ApplicationFor>(manager.getApplicationsFor());

		result = new ModelAndView("applicationFor/list");
		result.addObject("applicationFor", applicationFor);
		result.addObject("date", date);
		result.addObject("requestURI", "applicationFor/manager/list.do");

		return result;

	}

	//Display--------------------------------------------------------------------------------------
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int applicationforId) throws ParseException {
		ModelAndView result;
		ApplicationFor applicationfor = new ApplicationFor();
		boolean morethirtyDays;

		applicationfor = this.applicationForService.findOne(applicationforId);
		morethirtyDays = this.applicationForService.moreThanThirtyDays(applicationfor.getTrip().getStartDate());

		result = new ModelAndView("applicationFor/display");
		result.addObject("applicationFor", applicationfor);
		result.addObject("morethirtyDays", morethirtyDays);
		result.addObject("requestURI", "applicationFor/display.do");

		return result;
	}
}