
package controllers.ranger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.RangerService;
import controllers.AbstractController;
import domain.Ranger;

@Controller
@RequestMapping("/ranger/ranger")
public class EditRangerController extends AbstractController {

	// Services---------------------------------------------------------

	@Autowired
	private RangerService	rangerService;


	//Constructor--------------------------------------------------------

	public EditRangerController() {
		super();
	}

	//Edition------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		Ranger ranger;

		ranger = this.rangerService.findByPrincipal();
		Assert.notNull(ranger);
		result = this.createEditModelAndView(ranger);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final Ranger ranger, final BindingResult bindingResult) {
		ModelAndView result;

		if (bindingResult.hasErrors())
			result = this.createEditModelAndView(ranger);
		else
			try {
				this.rangerService.save(ranger);
				result = new ModelAndView("redirect:/welcome/index.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(ranger, "ranger.commit.error");
			}

		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final Ranger ranger) {

		ModelAndView result;
		result = this.createEditModelAndView(ranger, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Ranger ranger, final String message) {

		ModelAndView result;

		result = new ModelAndView("ranger/edit");
		result.addObject("ranger", ranger);
		result.addObject("message", message);

		return result;

	}
}