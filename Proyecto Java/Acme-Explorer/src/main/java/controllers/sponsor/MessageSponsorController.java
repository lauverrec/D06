
package controllers.sponsor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.MessageService;
import controllers.AbstractController;
import domain.Message;

@Controller
@RequestMapping("/message/sponsor")
public class MessageSponsorController extends AbstractController {

	// Services---------------------------------------------------------

	@Autowired
	private MessageService	messageService;


	//Constructor--------------------------------------------------------

	public MessageSponsorController() {
		super();
	}

	//	Listing ---------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		Collection<Message> mess;

		mess = this.messageService.findAll();

		result = new ModelAndView("message/list");
		result.addObject("messages", mess);
		return result;

	}
}
