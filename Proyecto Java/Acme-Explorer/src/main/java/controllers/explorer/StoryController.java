
package controllers.explorer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ExplorerService;
import services.StoryService;
import controllers.AbstractController;
import domain.Attachment;
import domain.Explorer;

@Controller
@RequestMapping("/story/explorer")
public class StoryController extends AbstractController {

	@Autowired
	private ExplorerService	explorerService;
	@Autowired
	private StoryService	storyService;


	public StoryController() {
		super();
	}

	//List---------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Explorer explorer;
		explorer = this.explorerService.findByPrincipal();

		result = new ModelAndView("story/list");
		result.addObject("stories", explorer.getStories());

		return result;

	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Explorer sponsor) {

		ModelAndView result;
		result = this.createEditModelAndView(sponsor, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Explorer explorer, final String message) {

		ModelAndView result;
		Collection<Attachment> attachments;
		attachments = this.storyService.urlAttachments(explorer);
		result = new ModelAndView("explorer/edit");
		result.addObject("explorer", explorer);
		result.addObject("attachments", attachments);
		result.addObject("message", message);

		return result;

	}

}
