
package controllers.auditor;

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

import services.AuditRecordService;
import controllers.AbstractController;
import domain.Attachment;
import domain.AuditRecord;

@Controller
@RequestMapping("/auditRecord/auditor")
public class AuditRecordAuditorController extends AbstractController {

	// Services---------------------------------------------------------

	@Autowired
	private AuditRecordService	auditRecordService;


	//Constructor--------------------------------------------------------

	public AuditRecordAuditorController() {
		super();
	}

	//Listing------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		final ModelAndView result;
		Collection<AuditRecord> auditRecords;

		auditRecords = this.auditRecordService.findByPrincipal();

		result = new ModelAndView("auditRecord/list");
		result.addObject("auditRecords", auditRecords);
		return result;

	}
	// Display ----------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int auditRecordId) {
		ModelAndView result;
		AuditRecord auditRecord = new AuditRecord();
		Collection<Attachment> attachments;

		attachments = auditRecord.getAttachments();

		auditRecord = this.auditRecordService.findOne(auditRecordId);
		result = new ModelAndView("auditRecord/display");
		result.addObject("auditRecord", auditRecord);
		result.addObject("attachments", attachments);

		return result;
	}

	//Creation-----------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		AuditRecord auditRecord;

		auditRecord = this.auditRecordService.create();
		result = this.createEditModelAndView(auditRecord);
		return result;

	}

	//Edition------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int auditRecordId) {
		ModelAndView result;
		AuditRecord auditRecord;

		auditRecord = this.auditRecordService.findOne(auditRecordId);
		Assert.notNull(auditRecord);
		result = this.createEditModelAndView(auditRecord);
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final AuditRecord auditRecord, final BindingResult bindingResult) {
		ModelAndView result;

		if (bindingResult.hasErrors())
			result = this.createEditModelAndView(auditRecord);
		else
			try {
				this.auditRecordService.save(auditRecord);
				result = new ModelAndView("redirect:list.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(auditRecord, "auditRecord.commit.error");
			}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@ModelAttribute final AuditRecord auditRecord, final BindingResult bindingResult) {
		ModelAndView result;

		try {
			this.auditRecordService.delete(auditRecord);
			result = new ModelAndView("redirect:list.do");
		} catch (final Throwable oops) {
			result = this.createEditModelAndView(auditRecord, "auditRecord.commit.error");
		}

		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(final AuditRecord auditRecord) {
		assert auditRecord != null;
		ModelAndView result;
		result = this.createEditModelAndView(auditRecord, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final AuditRecord auditRecord, final String message) {

		assert auditRecord != null;
		ModelAndView result;

		result = new ModelAndView("auditRecord/edit");
		result.addObject("auditRecord", auditRecord);
		result.addObject("message", message);

		return result;

	}

}
