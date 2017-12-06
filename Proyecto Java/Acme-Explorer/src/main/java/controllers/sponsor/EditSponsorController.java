<<<<<<< HEAD:Proyecto Java/Acme-Explorer/src/main/java/controllers/sponsor/EditSponsorController.java
<<<<<<< HEAD:Proyecto Java/Acme-Explorer/src/main/java/controllers/sponsor/SponsorController.java
<<<<<<< HEAD:Proyecto Java/Acme-Explorer/src/main/java/controllers/sponsor/SponsorController.java
=======
>>>>>>> parent of c7c0e16... Revert "Revert "Merge branch 'master' of https://github.com/lauverrec/D06"":Proyecto Java/Acme-Explorer/src/main/java/controllers/sponsor/SponsorController.java

package controllers.sponsor;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.SponsorService;
import controllers.AbstractController;
import domain.Sponsor;

@Controller
@RequestMapping("/sponsor")
public class SponsorController extends AbstractController {

	// Services---------------------------------------------------------

	@Autowired
	private SponsorService	sponsorService;


	//Constructor--------------------------------------------------------

	public SponsorController() {
		super();
	}

	//Edition------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Sponsor sponsor;

		sponsor = this.sponsorService.findByPrincipal();
		Assert.notNull(sponsor);
		result = this.createEditModelAndView(sponsor);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Sponsor sponsor, final BindingResult bindingResult) {
		ModelAndView result;

		if (bindingResult.hasErrors())
			result = this.createEditModelAndView(sponsor);
		else
			try {
				this.sponsorService.save(sponsor);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(sponsor, "sponsor.commit.error");
			}

		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Sponsor sponsor) {

		ModelAndView result;
		result = this.createEditModelAndView(sponsor, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Sponsor sponsor, final String message) {

		ModelAndView result;

		result = new ModelAndView("sponsor/edit");
		result.addObject("sponsor", sponsor);
		result.addObject("message", message);

		return result;

	}
}
<<<<<<< HEAD:Proyecto Java/Acme-Explorer/src/main/java/controllers/sponsor/EditSponsorController.java
=======

package controllers.sponsor;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.SponsorService;
import controllers.AbstractController;
import domain.Sponsor;

@Controller
@RequestMapping("/sponsor")
public class SponsorController extends AbstractController {

	// Services---------------------------------------------------------

	@Autowired
	private SponsorService	sponsorService;


	//Constructor--------------------------------------------------------

	public SponsorController() {
		super();
	}

	//Edition------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Sponsor sponsor;

		sponsor = this.sponsorService.findByPrincipal();
		Assert.notNull(sponsor);
		result = this.createEditModelAndView(sponsor);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Sponsor sponsor, final BindingResult bindingResult) {
		ModelAndView result;

		if (bindingResult.hasErrors())
			result = this.createEditModelAndView(sponsor);
		else
			try {
				this.sponsorService.save(sponsor);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(sponsor, "sponsor.commit.error");
			}

		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Sponsor sponsor) {

		ModelAndView result;
		result = this.createEditModelAndView(sponsor, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Sponsor sponsor, final String message) {

		ModelAndView result;

		result = new ModelAndView("sponsor/edit");
		result.addObject("sponsor", sponsor);
		result.addObject("message", message);

		return result;

	}
}
>>>>>>> ec1712c825764c9b4dd9290f8e6e0aa93d4ce834:Proyecto Java/Acme-Explorer/src/main/java/controllers/sponsor/SponsorController.java
=======

package controllers.sponsor;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.SponsorService;
import controllers.AbstractController;
import domain.Sponsor;

@Controller
@RequestMapping("/sponsor")
public class EditSponsorController extends AbstractController {

	// Services---------------------------------------------------------

	@Autowired
	private SponsorService	sponsorService;


	//Constructor--------------------------------------------------------

	public EditSponsorController() {
		super();
	}

	//Edition------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Sponsor sponsor;

		sponsor = this.sponsorService.findByPrincipal();
		Assert.notNull(sponsor);
		result = this.createEditModelAndView(sponsor);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Sponsor sponsor, final BindingResult bindingResult) {
		ModelAndView result;

		if (bindingResult.hasErrors())
			result = this.createEditModelAndView(sponsor);
		else
			try {
				this.sponsorService.save(sponsor);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(sponsor, "sponsor.commit.error");
			}

		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Sponsor sponsor) {

		ModelAndView result;
		result = this.createEditModelAndView(sponsor, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Sponsor sponsor, final String message) {

		ModelAndView result;

		result = new ModelAndView("sponsor/edit");
		result.addObject("sponsor", sponsor);
		result.addObject("message", message);

		return result;

	}
}
>>>>>>> parent of d240815... MessageFolder casi al 100:Proyecto Java/Acme-Explorer/src/main/java/controllers/sponsor/EditSponsorController.java
=======
>>>>>>> parent of c7c0e16... Revert "Revert "Merge branch 'master' of https://github.com/lauverrec/D06"":Proyecto Java/Acme-Explorer/src/main/java/controllers/sponsor/SponsorController.java
