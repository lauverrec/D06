/*
 * CurriculumCustomerController.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package controllers.contactEmergency;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ContactEmergencyService;
import services.ExplorerService;
import controllers.AbstractController;
import domain.ContactEmergency;

@Controller
@RequestMapping("/contactsEmergency/explorer")
public class ContactEmergencyController extends AbstractController {

	//service---------------------------------------------------------------------------
	@Autowired
	private ContactEmergencyService	contactEmergencyService;
	@Autowired
	private ExplorerService			explorerService;


	//constructor-------------------------------------------------------------------------
	public ContactEmergencyController() {
		super();
	}
	//Listing---------------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<ContactEmergency> contactsEmergency;
		contactsEmergency = this.explorerService.findByPrincipalContacts();

		result = new ModelAndView("contactsEmergency/list");
		result.addObject("contactsEmergency", contactsEmergency);

		return result;

	}
	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		ContactEmergency contactEmergency;

		contactEmergency = this.contactEmergencyService.create();
		result = this.createEditModelAndView(contactEmergency);

		return result;
	}
	//Edition--------------------------------------------------------------------------------

	//ancially methods---------------------------------------------------------------------------

	protected ModelAndView createEditModelAndView(final ContactEmergency contactsEmergency) {
		Assert.notNull(contactsEmergency);
		ModelAndView result;
		result = this.createEditModelAndView(contactsEmergency, null);
		return result;

	}

	protected ModelAndView createEditModelAndView(final ContactEmergency contactsEmergency, final String message) {
		Assert.notNull(contactsEmergency);
		ModelAndView result;

		result = new ModelAndView("conctactEmergency/edit");
		result.addObject("contactsEmergency", contactsEmergency);
		result.addObject("message", message);
		return result;

	}
}
