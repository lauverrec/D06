
package controllers.explorer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ExplorerService;
import controllers.AbstractController;
import domain.Explorer;

@Controller
@RequestMapping("/story/explorer")
public class StoryController extends AbstractController {

	@Autowired
	private ExplorerService	explorerService;


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

}
