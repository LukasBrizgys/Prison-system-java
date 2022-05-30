package lt.ku.prison.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lt.ku.prison.classes.PrisonerVisitorKey;
import lt.ku.prison.entities.PrisonerVisitor;
import lt.ku.prison.services.PrisonerService;
import lt.ku.prison.services.PrisonerVisitorService;
import lt.ku.prison.services.VisitorService;

@Controller
public class PrisonerVisitorController {
	@Autowired
	PrisonerService prisonerService;
	
	@Autowired
	VisitorService visitorService;
	
	@Autowired
	PrisonerVisitorService prisonerVisitorService;
	
	@GetMapping("/prisoner/newVisitor/")
	String home(Model model) {
		model.addAttribute("prisonerVisitor", new PrisonerVisitor());
		model.addAttribute("prisonerVisitorKey", new PrisonerVisitorKey());
		model.addAttribute("prisoners", prisonerService.getAllPrisoners());
		model.addAttribute("visitors", visitorService.getAllVisitors());
		
		return "prisoner_visitor_new";
	}
	@PostMapping("/prisoner/newVisitor/")
	String add(@Valid
			@ModelAttribute PrisonerVisitor prisonerVisitor,
			BindingResult prisonerVisitorResult,
			@Valid
			@ModelAttribute PrisonerVisitorKey prisonerVisitorKey,
			BindingResult prisonerVisitorKeyResult,
			@RequestParam("prisonerId") Integer prisonerId,
			@RequestParam("visitorId") Integer visitorId,
			Model model
			) {
		
		if(prisonerVisitorResult.hasErrors() || prisonerVisitorKeyResult.hasErrors()) {
			model.addAttribute("prisoners", prisonerService.getAllPrisoners());
			model.addAttribute("visitors", visitorService.getAllVisitors());
			return "prisoner_visitor_new";
		}
		prisonerVisitorKey.setPrisonerId(prisonerId);
		prisonerVisitorKey.setVisitorId(visitorId);
		prisonerVisitor.setId(prisonerVisitorKey);
		prisonerVisitor.setPrisoner(prisonerService.getPrisoner(prisonerId));
		prisonerVisitor.setVisitor(visitorService.getVisitor(visitorId));
		prisonerVisitorService.addPrisonerVisitor(prisonerVisitor);
		
		return "redirect:/prisoner/";
		
	}
	@GetMapping("/prisoner/visitors/{id}")
	String visitors(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("prisonerVisitors", prisonerVisitorService.getVisitorsByPrisonerId(id));
		return "prisoner_visitor_list";
	}
	
	@PostMapping("/prisoner/visitors/delete/")
	String deleteVisitor(@RequestParam("prisonerId") Integer prisonerId,
			@RequestParam("visitorId") Integer visitorId,
			@RequestParam("startDate") String startDate) {
		
		prisonerVisitorService.deletePrisonerVisitor(prisonerId, visitorId, startDate);
		return "redirect:/prisoner/visitors/"+prisonerId;
	}
	
}
