
package controllers.administrator;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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

		suspicious = this.actorService.isSuspicious();

		result = new ModelAndView("administrator/suspicious");
		result.addObject("suspicious", suspicious);

		return result;

	}

	@RequestMapping(value = "/ban", method = RequestMethod.GET)
	public ModelAndView cancel(@RequestParam final int actorId) {
		ModelAndView result;
		Actor actor;
		actor = this.actorService.findOne(actorId);
		result = this.createBanModelAndView(actor);

		return result;
	}

	@RequestMapping(value = "/ban", method = RequestMethod.POST, params = "ban")
	public ModelAndView cancel(@Valid Actor actor, BindingResult binding) {
		ModelAndView result;
		//Collection<ApplicationFor> applicationsFor;	
		if (binding.hasErrors())
			result = this.createBanModelAndView(actor);
		else
			try {
				this.actorService.ban(actor);
				this.actorService.save(actor);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createBanModelAndView(actor, "applicationfor.cancel.error");
			}
		return result;
	}

	protected ModelAndView createBanModelAndView(final Actor ban) {
		ModelAndView result;

		result = this.createBanModelAndView(ban, null);
		return result;
	}

	private ModelAndView createBanModelAndView(final Actor actor, final String message) {
		ModelAndView result;

		result = new ModelAndView("applicationFor/");
		result.addObject("applicationFor", actor);
		result.addObject("message", message);

		return result;
	}

}
