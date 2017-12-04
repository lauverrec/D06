
package controllers.sponsor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.SponsorshipService;
import controllers.AbstractController;
import domain.Sponsorship;
import domain.Trip;

@Controller
@RequestMapping("/sponsorship/sponsor")
public class TripSponsorshipController extends AbstractController {

	// Services---------------------------------------------------------

	@Autowired
	private SponsorshipService	sponsorshipService;


	//Constructor--------------------------------------------------------

	public TripSponsorshipController() {
		super();
	}

	// Display ----------------------------------------------------------------
	@RequestMapping(value = "/displayTrip", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam Sponsorship sponsorship) {
		ModelAndView result;
		Trip trip;

		trip = this.sponsorshipService.findByPrincipalSponsorhipTrip(sponsorship);
		result = new ModelAndView("sponsorship/displayTrip");
		result.addObject("trip", trip);

		return result;
	}

}
