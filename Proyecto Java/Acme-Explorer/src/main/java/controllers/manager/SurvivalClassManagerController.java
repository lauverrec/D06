
package controllers.manager;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.SurvivalClassService;
import controllers.AbstractController;
import domain.SurvivalClass;

@Controller
@RequestMapping("/survivalClass/manager")
public class SurvivalClassManagerController extends AbstractController {

	// Services---------------------------------------------------------

	@Autowired
	private SurvivalClassService	survivalClassService;


	//Constructor--------------------------------------------------------

	public SurvivalClassManagerController() {
		super();
	}

	//Listing------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		Collection<SurvivalClass> survivalClass;

		survivalClass = this.survivalClassService.findSurvivalClassByManager();

		result = new ModelAndView("survivalClass/list");
		result.addObject("requestURI", "survivalClass/manager/list.do");
		result.addObject("survivalClasses", survivalClass);
		return result;

	}

}
