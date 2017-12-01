/* CurriculumCustomerController.java
 *
 * Copyright (C) 2015 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers.customer;

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

import services.CurriculumService;
import controllers.AbstractController;
import domain.Curriculum;

@Controller
@RequestMapping("/curriculum/customer")
public class CurriculumCustomerController extends AbstractController {
	
	// Services ---------------------------------------------------------------
	
	@Autowired
	private CurriculumService curriculumService;
		
	// Constructors -----------------------------------------------------------
	
	public CurriculumCustomerController() {
		super();
	}
	
	// Listing ----------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Curriculum> curricula;

		curricula = curriculumService.findByPrincipal();
		result = new ModelAndView("curriculum/list");			
		result.addObject("curricula", curricula);

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Curriculum curriculum;

		curriculum = curriculumService.create();
		result = createEditModelAndView(curriculum);

		return result;
	}

	// Edition ----------------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int curriculumId) {
		ModelAndView result;
		Curriculum curriculum;

		curriculum = curriculumService.findOneToEdit(curriculumId);		
		Assert.notNull(curriculum);
		result = createEditModelAndView(curriculum);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Curriculum curriculum, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(curriculum);
		} else {
			try {
				curriculumService.save(curriculum);				
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(curriculum, "curriculum.commit.error");				
			}
		}

		return result;
	}
			
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Curriculum curriculum, BindingResult binding) {
		ModelAndView result;

		try {			
			curriculumService.delete(curriculum);
			result = new ModelAndView("redirect:list.do");						
		} catch (Throwable oops) {
			result = createEditModelAndView(curriculum, "curriculum.commit.error");
		}

		return result;
	}
	
	// Ancillary methods ------------------------------------------------------
	
	protected ModelAndView createEditModelAndView(Curriculum curriculum) {
		ModelAndView result;

		result = createEditModelAndView(curriculum, null);
		
		return result;
	}	
	
	protected ModelAndView createEditModelAndView(Curriculum curriculum, String message) {
		ModelAndView result;
					
		result = new ModelAndView("curriculum/edit");
		result.addObject("curriculum", curriculum);
		result.addObject("message", message);

		return result;
	}
		
}