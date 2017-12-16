
package controllers.manager;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.MessageFolderService;
import services.MessageService;
import controllers.AbstractController;
import domain.Actor;
import domain.Message;
import domain.MessageFolder;

@Controller
@RequestMapping("/message/manager")
public class MessageManagerController extends AbstractController {

	// Services -------------------------------------------------------------------

	@Autowired
	private MessageService			messageService;

	@Autowired
	private ActorService			actorService;

	@Autowired
	private MessageFolderService	messageFolderService;


	// Constructors ---------------------------------------------------------------

	public MessageManagerController() {
		super();
	}

	// Listing methods -----------------------------------------------------------
	//Funciona bien
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int messageFolderId) {

		ModelAndView result;
		Collection<Message> msgs;

		msgs = this.messageService.messagesOfFolder(messageFolderId);

		result = new ModelAndView("message/list");
		result.addObject("messages", msgs);

		return result;

	}

	//Display
	//Funciona bien 
	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int messageId) {

		ModelAndView result;
		Message message;

		message = this.messageService.findOne(messageId);
		result = new ModelAndView("message/display");
		result.addObject("messageDisplay", message);

		return result;
	}

	// Creation and edition methods ------------------------------------

	//Change Folder------------------------------------------------------------------
	//Funciona bien
	@RequestMapping(value = "/changefolder", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int messageId) {

		ModelAndView result;

		Message message;
		message = this.messageService.findOne(messageId);
		Assert.notNull(message);
		result = new ModelAndView("message/changeFolder");

		Actor actor = this.actorService.findPrincipal();
		Collection<MessageFolder> folders = this.messageFolderService.ActorFolders(actor.getId());
		folders.remove(message.getMessageFolder());
		result.addObject("folders", folders);
		result.addObject("message", null);
		result.addObject("msg", message);

		return result;

	}

	//Funciona bien
	@RequestMapping(value = "/changefolder", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid Message m, BindingResult binding, @RequestParam int messageId) {
		ModelAndView result;
		MessageFolder messageFolderOldMessage = this.messageService.findOne(messageId).getMessageFolder();
		if (binding.hasErrors()) {
			Message originalMessage = this.messageService.findOne(messageId);
			result = new ModelAndView("message/changeFolder");

			Actor actor = this.actorService.findPrincipal();
			Collection<MessageFolder> folders = this.messageFolderService.ActorFolders(actor.getId());
			folders.remove(originalMessage.getMessageFolder());
			result.addObject("folders", folders);
			result.addObject("message", null);
			result.addObject("m", m);
		} else
			try {
				this.messageService.save(m);
				result = new ModelAndView("redirect:list.do?messageFolderId=" + messageFolderOldMessage.getId());
			} catch (Throwable oops) {
				Message originalMessage = this.messageService.findOne(messageId);
				result = new ModelAndView("message/changefolder");

				Actor actor = this.actorService.findPrincipal();
				Collection<MessageFolder> folders = this.messageFolderService.ActorFolders(actor.getId());
				folders.remove(originalMessage.getMessageFolder());
				result.addObject("folders", folders);
				result.addObject("message", null);
				result.addObject("m", "msg.commit.error");
			}
		return result;
	}

	//Funciona flama
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView deleteMessage(@RequestParam int messageId) {
		ModelAndView result;
		Message messageToDelete;
		messageToDelete = this.messageService.findOne(messageId);

		try {

			this.messageService.delete(messageToDelete);
			result = new ModelAndView("redirect:list.do?messageFolderId=" + messageToDelete.getMessageFolder().getId());
		} catch (Throwable oops) {
			result = this.createNewModelAndView(messageToDelete, "message.commit.error");

		}

		return result;
	}

	@RequestMapping(value = "/send", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;

		Message message;
		message = this.messageService.create();

		result = this.createNewModelAndView(message);
		result.addObject("requestURI", "message/manager/send.do");

		return result;

	}
	@RequestMapping(value = "/send", method = RequestMethod.POST, params = "save")
	public ModelAndView send(@ModelAttribute("m") @Valid Message m, BindingResult binding) {
		ModelAndView result;
		if (binding.hasErrors())
			result = this.createNewModelAndView(m);
		else
			try {
				MessageFolder folderToReturn = m.getMessageFolder();
				this.messageService.save(m);
				result = new ModelAndView("redirect:list.do?messageFolderId=" + folderToReturn.getId());
			} catch (Throwable oops) {

				result = this.createNewModelAndView(m, "message.commit.error");

			}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createNewModelAndView(Message m) {
		ModelAndView result;
		result = this.createNewModelAndView(m, null);
		return result;
	}

	protected ModelAndView createNewModelAndView(Message m, String message) {
		ModelAndView result;

		result = new ModelAndView("message/send");

		Actor actor = this.actorService.findPrincipal();
		Collection<Actor> actors = this.actorService.findAll();
		actors.remove(actor);

		result.addObject("actors", actors);
		String low = "LOW";
		String neutral = "NEUTRAL";
		String high = "HIGH";
		Collection<String> priorities = new ArrayList<String>();
		priorities.add(low);
		priorities.add(neutral);
		priorities.add(high);

		result.addObject("message", message);
		result.addObject("priorities", priorities);
		result.addObject("m", m);
		return result;
	}

}
