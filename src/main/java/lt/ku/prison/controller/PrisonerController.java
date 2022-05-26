package lt.ku.prison.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lt.ku.prison.services.PrisonerService;

@RequestMapping("/prisoner")
@Controller
public class PrisonerController {
	@Autowired
	PrisonerService prisonerService;
	
	@RequestMapping("/")
	public String prisoners(Model model) {
		model.addAttribute("prisoners", prisonerService.getAllPrisoners());
		return "prisoner_list";
	}
	
}
