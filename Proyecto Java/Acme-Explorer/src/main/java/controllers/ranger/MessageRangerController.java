
package controllers.ranger;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.MessageFolderService;
import services.MessageService;
import controllers.AbstractController;
import domain.Actor;
import domain.Message;
import domain.MessageFolder;

@Controller
@RequestMapping("/message/ranger")
public class MessageRangerController extends AbstractController {

	// Services---------------------------------------------------------

	@Autowired
	private MessageService			messageService;

	@Autowired
	private MessageFolderService	messageFolderService;


	//Constructor--------------------------------------------------------

	public MessageRangerController() {
		super();
	}

	//	Listing ---------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int messageFolderId) {

		ModelAndView result;
		Collection<Message> mess;

		mess = this.messageService.messagesOfFolder(messageFolderId);

		result = new ModelAndView("message/list");
		result.addObject("messages", mess);
		return result;

	}

	//Edition------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int messageId) {
		ModelAndView result;
		Message messag;

		messag = this.messageService.findOne(messageId);
		Assert.notNull(messag);
		result = this.createEditModelAndView(messag);

		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Message mess, final BindingResult bindingResult) {
		ModelAndView result;

		if (bindingResult.hasErrors())
			result = this.createEditModelAndView(mess);
		else
			try {
				this.messageService.save(mess);
				result = new ModelAndView("redirect:/messageFolder/ranger/list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(mess, "message.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Message messag, BindingResult bindingResult) {
		ModelAndView result;

		try {
			this.messageService.delete(messag);
			result = new ModelAndView("redirect:list.do?messageFolderId=" + messag.getMessageFolder().getId());
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(messag, "message.commit.error");
		}

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

	// Ancillary methods ------------------------------------------------------
	protected ModelAndView createEditModelAndView(Message messag) {
		ModelAndView result;
		result = this.createEditModelAndView(messag, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(Message messag, String messageCode) {
		ModelAndView result;
		Collection<MessageFolder> messageFolders;
		Actor sender;
		Actor recipient;

		messageFolders = this.messageFolderService.findAllByActorAutenticate();

		sender = messag.getSender();
		recipient = messag.getRecipient();

		result = new ModelAndView("message/edit");
		result.addObject("mess", messag);
		result.addObject("messageFolders", messageFolders);
		result.addObject("sender", sender);
		result.addObject("recipient", recipient);
		result.addObject("messageCode", messageCode);
		result.addObject("requestURI", "message/ranger/edit.do");

		return result;

	}
}
