
package controllers.sponsor;

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

import controllers.AbstractController;

import services.MessageFolderService;
import domain.Message;
import domain.MessageFolder;

@Controller
@RequestMapping(value = "/messageFolder/sponsor")
public class MessageFolderSponsorController extends AbstractController {

	//	Services --------------------------------------------------------

	@Autowired
	private MessageFolderService	messageFolderService;


	//	Constructors

	public MessageFolderSponsorController() {
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

	// Edit ----------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int messageFolderId) {
		ModelAndView result;
		MessageFolder messageFolder;

		messageFolder = this.messageFolderService.findOne(messageFolderId);
		Assert.notNull(messageFolder);
		result = this.createEditModelAndView(messageFolder);

		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid MessageFolder messageFolder, BindingResult bindingResult) {
		ModelAndView result;

		if (bindingResult.hasErrors())
			result = this.createEditModelAndView(messageFolder);
		else
			try {
				this.messageFolderService.save(messageFolder);
				result = new ModelAndView("redirect:/messageFolder/list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(messageFolder, "messageFolder.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(MessageFolder messageFolder, BindingResult bindingResult) {
		ModelAndView result;

		try {
			this.messageFolderService.delete(messageFolder);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(messageFolder, "messageFolder.commit.error");
		}

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
