
package controllers.auditor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.MessageService;
import controllers.AbstractController;
import domain.Message;

@Controller
@RequestMapping("/message/auditor")
public class MessageAuditorController extends AbstractController {

	// Services---------------------------------------------------------

	@Autowired
	private MessageService	messageService;


	//Constructor--------------------------------------------------------

	public MessageAuditorController() {
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

	// Display ----------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int messageId) {
		ModelAndView result;
		Message mess;

		mess = this.messageService.findOne(messageId);
		result = new ModelAndView("message/display");
		result.addObject("mess", mess);

		return result;
	}
}
