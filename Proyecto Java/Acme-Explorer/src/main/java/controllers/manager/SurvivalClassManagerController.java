
package controllers.manager;

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

	// Display ----------------------------------------------------------------
	//
	//	@RequestMapping(value = "/display", method = RequestMethod.GET)
	//	public ModelAndView display(@RequestParam final int survivalClasssId) {
	//		ModelAndView result;
	//		SurvivalClass survivalClass;
	//
	//		survivalClass = this.survivalClassService.findOne(survivalClasssId);
	//		result = new ModelAndView("survivalClass/display");
	//		result.addObject("requestURI", "survivalClass/manager/display.do");
	//		result.addObject("survivalClass", survivalClass);
	//
	//		return result;
	//	}

	//Creation-----------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		SurvivalClass survivalClass;

		survivalClass = this.survivalClassService.create();
		result = this.createEditModelAndView(survivalClass);
		return result;

	}

	//Edition------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int survivalClassId) {
		ModelAndView result;
		SurvivalClass survivalClass;

		survivalClass = this.survivalClassService.findOne(survivalClassId);
		Assert.notNull(survivalClass);
		result = this.createEditModelAndView(survivalClass);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final SurvivalClass survivalClass, final BindingResult bindingResult) {
		ModelAndView result;

		if (bindingResult.hasErrors())
			result = this.createEditModelAndView(survivalClass);
		else
			try {
				this.survivalClassService.save(survivalClass);
				result = new ModelAndView("redirect:list.do");

			} catch (final Throwable oops) {
				result = this.createEditModelAndView(survivalClass, "survivalClass.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@ModelAttribute final SurvivalClass survivalClass, final BindingResult bindingResult) {
		ModelAndView result;

		try {
			this.survivalClassService.delete(survivalClass);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(survivalClass, "survivalClass.commit.error");
		}

		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final SurvivalClass survivalClass) {
		assert survivalClass != null;
		ModelAndView result;
		result = this.createEditModelAndView(survivalClass, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final SurvivalClass survivalClass, final String message) {

		assert survivalClass != null;
		ModelAndView result;

		result = new ModelAndView("survivalClass/edit");
		result.addObject("survivalClass", survivalClass);
		result.addObject("message", message);

		return result;

	}

}
