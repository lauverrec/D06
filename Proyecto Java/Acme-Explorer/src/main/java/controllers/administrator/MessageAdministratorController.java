
package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.MessageService;
import domain.Message;

@Controller
@RequestMapping("/message/administrator")
public class MessageAdministratorController {

	// Services---------------------------------------------------------

	@Autowired
	private MessageService	messageService;


	//Constructor--------------------------------------------------------

	public MessageAdministratorController() {
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
