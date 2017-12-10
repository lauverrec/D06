
package controllers.ranger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CurriculaService;
import services.RangerService;
import controllers.AbstractController;
import domain.Curricula;
import domain.Ranger;

@Controller
@RequestMapping("/curricula/ranger")
public class CurriculaRangerController extends AbstractController {

	// Services---------------------------------------------------------

	@Autowired
	private CurriculaService	curriculaService;
	@Autowired
	private RangerService		rangerService;


	//Constructor--------------------------------------------------------

	public CurriculaRangerController() {
		super();
	}

	// Display ----------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display() {
		ModelAndView result;
		Curricula curricula;
		Ranger rangerPrincipal;

		rangerPrincipal = this.rangerService.findByPrincipal();
		curricula = this.curriculaService.findCurriculaFromRanger(rangerPrincipal.getId());

		if (curricula == null)
			result = new ModelAndView("redirect:/curricula/ranger/create.do");
		else {
			result = new ModelAndView("curricula/display");
			result.addObject("curricula", curricula);
			result.addObject("miscellaneousRecord", curricula.getMiscellaneousRecords());
			result.addObject("endorserRecord", curricula.getEndorserRecords());
			result.addObject("professionalRecord", curricula.getProfessionalRecords());
			result.addObject("educationRecord", curricula.getEducationRecords());
			result.addObject("personalRecord", curricula.getPersonalRecord());
		}

		return result;
	}
	//Creation-----------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Curricula curricula;

		curricula = this.curriculaService.create();
		curricula.setRanger(this.rangerService.findByPrincipal());

		result = new ModelAndView("curricula/edit");
		result.addObject("curricula", curricula);

		return result;

	}

	//Edition------------------------------------------------------------

	// Ancillary methods ------------------------------------------------------

}
