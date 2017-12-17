
package controllers.explorer;

import java.util.Collection;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ExplorerService;
import services.FinderService;
import services.TripService;
import domain.Explorer;
import domain.Finder;
import domain.Trip;

@Controller
@RequestMapping("/finder/explorer")
public class FinderExplorerController {

	// Services---------------------------------------------------------

	@Autowired
	private ExplorerService	explorerService;
	@Autowired
	private FinderService	finderService;
	@Autowired
	private TripService		tripService;


	//Constructor--------------------------------------------------------

	public FinderExplorerController() {
		super();
	}
	// Search -----------------------------------------------------------------

	@RequestMapping(value = "/search", method = RequestMethod.POST, params = "search")
	public ModelAndView listByKeywordPriceDate(@Valid final Finder finder, final BindingResult bindingResult) {
		ModelAndView result;
		final Finder finderCache;
		Finder finderResult;

		if (bindingResult.hasErrors())
			result = this.createEditModelAndView(finder);
		else
			try {
				result = new ModelAndView("finder/list");
				finderCache = this.explorerService.findByPrincipal().getFinder();
				final Date date = new Date();

				if (this.finderService.checkEquals(finderCache, finder) && (date.getTime() - finderCache.getStartCacheTime().getTime()) <= 3600000)
					finderResult = finderCache;
				else
					finderResult = this.finderService.search(finder);

				final double price = this.tripService.setPriceTrip(finderResult.getTrips());
				result = new ModelAndView("finder/list");
				result.addObject("trips", finderResult.getTrips());
				result.addObject("price", price);
				result.addObject("requestURI", "finder/explorer/search.do");

			} catch (final Throwable oops) {
				result = this.createEditModelAndView(finder, "finder.commit.error");
			}

		return result;

	}
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView listTrip() {
		ModelAndView result;
		Collection<Trip> trips;
		Explorer explorer;
		double price;
		Finder finder;

		explorer = this.explorerService.findByPrincipal();
		finder = explorer.getFinder();
		trips = finder.getTrips();
		price = this.tripService.setPriceTrip(trips);

		trips = this.tripService.findAll();

		result = new ModelAndView("finder/list");
		result.addObject("trips", trips);
		result.addObject("finder", finder);
		result.addObject("price", price);
		result.addObject("requestURI", "finder/explorer/search.do");

		return result;
	}

	protected ModelAndView createEditModelAndView(final Finder finder) {
		assert finder != null;
		ModelAndView result;
		result = this.createEditModelAndView(finder, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Finder finder, final String message) {

		assert finder != null;
		ModelAndView result;

		result = new ModelAndView("finder/list");
		result.addObject("finder", finder);
		result.addObject("message", message);

		return result;

	}
}
