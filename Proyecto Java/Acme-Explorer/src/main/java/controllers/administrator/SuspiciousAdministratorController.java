
package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AdministratorService;
import controllers.AbstractController;
import domain.Actor;

@Controller
@RequestMapping("/suspicious/administrator/")
public class SuspiciousAdministratorController extends AbstractController {

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private ActorService			actorService;


	//Listing

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<Actor> suspicious;

		suspicious = this.actorService.suspicious();

		result = new ModelAndView("administrator/suspicious");
		result.addObject("suspicious", suspicious);

		return result;

	}

	@RequestMapping(value = "/ban", method = RequestMethod.GET)
	public ModelAndView cancel(@RequestParam final int actorId) {
		ModelAndView result;
		Actor actor;
		actor = this.actorService.findOne(actorId);
		this.actorService.ban(actor);
		result = this.createBanModelAndView(actor);

		return result;
	}

	protected ModelAndView createBanModelAndView(final Actor ban) {
		ModelAndView result;

		result = this.createBanModelAndView(ban, null);
		return result;
	}

	protected ModelAndView createBanModelAndView(final Actor actor, final String message) {
		ModelAndView result;

		result = new ModelAndView("administrator/suspicious");
		result.addObject("message", message);

		return result;
	}

}
