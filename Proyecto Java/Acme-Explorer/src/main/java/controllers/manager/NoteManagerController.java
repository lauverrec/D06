
package controllers.manager;

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

import services.NoteService;
import controllers.AbstractController;
import domain.Note;

@Controller
@RequestMapping(value = "/note/manager")
public class NoteManagerController extends AbstractController {

	//Services--------------------------------------------

	@Autowired
	private NoteService	noteService;


	//Listing----------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		Collection<Note> notes;

		notes = this.noteService.findAll();
		//note/auditor/list.do

		result = new ModelAndView("note/list");
		result.addObject("requestURI", "note/manager/list.do");
		result.addObject("notes", notes);
		return result;

	}

	// Display ----------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int noteId) {
		ModelAndView result;
		Note note;

		note = this.noteService.findOne(noteId);

		result = new ModelAndView("note/display");
		result.addObject("note", note);
		result.addObject("requestURI", "note/manager/display.do");

		return result;
	}
	//Editing---------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int noteId) {

		ModelAndView result;
		Note note;

		note = this.noteService.findOne(noteId);
		Assert.notNull(note);

		result = this.createEditModelAndView(note);

		return result;
	}
	//Saving------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Note note, BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(note);
		else
			try {
				this.noteService.save(note);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = this.createEditModelAndView(note, "note.commit.error");
			}
		return result;
	}
	//auxiliares--------

	protected ModelAndView createEditModelAndView(Note note) {

		Assert.notNull(note);
		ModelAndView result;
		result = this.createEditModelAndView(note, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(Note note, String messageCode) {
		assert note != null;

		ModelAndView result;

		result = new ModelAndView("note/edit");
		result.addObject("note", note);
		result.addObject("message", messageCode);

		return result;

	}

}
