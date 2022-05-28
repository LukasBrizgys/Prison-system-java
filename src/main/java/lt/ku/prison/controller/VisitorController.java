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
import org.springframework.web.bind.annotation.RequestMapping;

import lt.ku.prison.entities.Visitor;
import lt.ku.prison.services.VisitorService;

@Controller
@RequestMapping("/visitor")
public class VisitorController {
	@Autowired
	VisitorService visitorService;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("visitors", visitorService.getAllVisitors());
		return "visitor_list";
	}
	
	@GetMapping("/new")
	public String create(Model model) {
		model.addAttribute("visitor", new Visitor());
		return "visitor_new";
	}
	
	@PostMapping("/new")
	public String newVisitor(@Valid
			@ModelAttribute Visitor visitor,
			BindingResult result
			) {
		if(result.hasErrors()) {
			return "visitor_new";
		}
		
		visitorService.addVisitor(visitor);
		return "redirect:/visitor/";
	}
	@GetMapping("/update/{id}")
	public String update(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("visitor", visitorService.getVisitor(id));
		return "visitor_update";
	}
	
	@PostMapping("/update/{id}")
	public String updateVisitor(@PathVariable("id") Integer id,
			@Valid
			@ModelAttribute Visitor visitor,
			BindingResult result
			) {
		if(result.hasErrors()) {
			return "visitor_update";
		}
		visitorService.updateVisitor(visitor);
		return "redirect:/visitor/";
	}
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("id", id);
		
		return "visitor_delete";
	}
	@PostMapping("/delete/{id}")
	public String deleteVisitor(@PathVariable("id") Integer id) {
		visitorService.deleteVisitor(id);
		return "redirect:/visitor/";
	}
	

}
