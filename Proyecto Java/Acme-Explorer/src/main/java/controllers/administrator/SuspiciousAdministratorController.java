
package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.UserAccount;
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


	//	//Listing
	//
	//	@RequestMapping(value = "/list", method = RequestMethod.GET)
	//	public ModelAndView list() {
	//
	//		ModelAndView result;
	//		Collection<Actor> suspicious;
	//
	//		suspicious = this.actorService.suspicious();
	//
	//		result = new ModelAndView("administrator/suspicious");
	//		result.addObject("suspicious", suspicious);
	//
	//		return result;
	//
	//	}

	// Listing ----------------------------------------------------------------

	@RequestMapping("/list_ban")
	public ModelAndView listSuspicious() {
		ModelAndView result;
		Collection<Actor> suspicious;

		suspicious = this.actorService.suspicious();
		result = new ModelAndView("administrator/suspicious");
		result.addObject("suspicious", suspicious);
		result.addObject("ban", false);
		result.addObject("requestURI", "suspicious/administrator/list_ban.do");

		return result;
	}

	@RequestMapping("/list_unban")
	public ModelAndView listBanner() {
		ModelAndView result;
		Collection<Actor> suspicious;

		suspicious = this.actorService.suspicious();
		result = new ModelAndView("administrator/suspicious");
		result.addObject("suspicious", suspicious);
		result.addObject("unban", true);
		result.addObject("requestURI", "suspicious/administrator/list_unban.do");

		return result;
	}

	@RequestMapping(value = "/ban", method = RequestMethod.GET)
	public ModelAndView register(@RequestParam final int actorId) {
		ModelAndView result;
		Actor actor;
		UserAccount userAccount;

		actor = this.actorService.findOne(actorId);
		userAccount = actor.getUserAccount();

		try {
			this.actorService.ban(userAccount);
			result = this.listSuspicious();
			result.addObject("message", "actor.commit.ok");
		} catch (final Throwable oops) {
			result = this.listSuspicious();
			result.addObject("message", "event.commit.error");
		}

		return result;
	}
	@RequestMapping(value = "/unban", method = RequestMethod.GET)
	public ModelAndView unregister(@RequestParam final int actorId) {
		ModelAndView result;
		Actor actor;
		UserAccount userAccount;

		actor = this.actorService.findOne(actorId);
		userAccount = actor.getUserAccount();

		try {
			this.actorService.unban(userAccount);
			result = this.listSuspicious();
			//result.addObject("message", "event.commit.ok");
		} catch (final Throwable oops) {
			result = this.listSuspicious();
			result.addObject("message", "event.commit.error");
		}

		return result;
	}

	//	@RequestMapping(value = "/ban", method = RequestMethod.GET)
	//	public ModelAndView ban(@RequestParam final int actorId) {
	//		ModelAndView result;
	//		Actor actor;
	//		actor = this.actorService.findOne(actorId);
	//		this.actorService.ban(actor);
	//		result = new ModelAndView("administrator/suspicious");
	//
	//		return result;
	//	}
	//
	//	@RequestMapping(value = "/unban", method = RequestMethod.GET)
	//	public ModelAndView unban(@RequestParam final int actorId) {
	//		ModelAndView result;
	//		Actor actor;
	//		actor = this.actorService.findOne(actorId);
	//		this.actorService.unban(actor);
	//		result = new ModelAndView("administrator/suspicious");
	//
	//		return result;
	//	}
	//
	//	protected ModelAndView createBanModelAndView(final Actor ban) {
	//		ModelAndView result;
	//
	//		result = this.createBanModelAndView(ban, null);
	//		return result;
	//	}
	//
	//	protected ModelAndView createBanModelAndView(final Actor actor, final String message) {
	//		ModelAndView result;
	//
	//		result = new ModelAndView("administrator/suspicious");
	//		result.addObject("message", message);
	//
	//		return result;
	//	}

}
