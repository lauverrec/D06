
package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AuditRecordService;
import domain.Attachment;
import domain.AuditRecord;

@Controller
@RequestMapping("/auditRecord")
public class AuditRecordController extends AbstractController {

	// Services---------------------------------------------------------

	@Autowired
	private AuditRecordService	auditRecordService;


	//Constructor--------------------------------------------------------

	public AuditRecordController() {
		super();
	}

	// Display ----------------------------------------------------------------

	@RequestMapping(value = "/displayaudit", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int tripId) {
		ModelAndView result;

		Collection<Attachment> attach;
		Collection<AuditRecord> audits;
		AuditRecord auditRecord;
		auditRecord = new AuditRecord();

		audits = this.auditRecordService.findAuditRecordsForTrip(tripId);
		attach = this.auditRecordService.urlAttachments(auditRecord);

		result = new ModelAndView("auditRecord/display");
		result.addObject("auditRecord", audits);
		result.addObject("attachments", attach);

		return result;
	}
}
