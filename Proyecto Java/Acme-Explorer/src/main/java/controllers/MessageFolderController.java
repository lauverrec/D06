
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.MessageFolderService;
import domain.Message;
import domain.MessageFolder;

@Controller
@RequestMapping(value = "/messageFolder")
public class MessageFolderController extends AbstractController {

	//	Services --------------------------------------------------------

	@Autowired
	private MessageFolderService	messageFolderService;


	//	Constructors

	public MessageFolderController() {
		super();
	}

	//	Listing ---------------------------------------------------------
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<MessageFolder> messageFolders;

		messageFolders = this.messageFolderService.findAllByActorAutenticate();

		result = new ModelAndView("messageFolder/list");
		result.addObject("messageFolders", messageFolders);

		return result;
	}

	// Creation-------------------------------------------------------
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		MessageFolder messageFolder;

		messageFolder = this.messageFolderService.create();
		result = this.createEditModelAndView(messageFolder);

		return result;

	}
	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final MessageFolder messageFolder) {
		ModelAndView result;
		result = this.createEditModelAndView(messageFolder, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final MessageFolder messageFolder, final String messageCode) {
		ModelAndView result;
		Collection<Message> messages;

		messages = messageFolder.getMessages();

		result = new ModelAndView("messageFolder/edit");
		result.addObject("messageFolder", messageFolder);
		result.addObject("messages", messages);
		result.addObject("messageCode", messageCode);

		return result;

	}

}
