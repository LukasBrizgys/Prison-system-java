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

import lt.ku.prison.entities.Crime;
import lt.ku.prison.services.CrimeService;

@Controller
@RequestMapping("/crime")
public class CrimeController {
	@Autowired
	CrimeService crimeService;
	
	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("crimes", crimeService.getAllCrimes());
		return "crime_list";
	}
	
	@GetMapping("/new")
	public String create(Model model) {
		model.addAttribute("crime", new Crime());
		return "crime_new";
	}
	
	@PostMapping("/new")
	public String newCrime(@Valid
			@ModelAttribute Crime crime,
			BindingResult result
			) {
		if(result.hasErrors()) {
			return "crime_new";
		}
		crimeService.addCrime(crime);
		return "redirect:/crime/";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("crime", crimeService.getCrime(id));
		return "crime_update";
	}
	@PostMapping("/update/{id}")
	public String updateCrime(@PathVariable("id") Integer id,
			@Valid @ModelAttribute Crime crime,
			BindingResult result
			) {
		crimeService.updateCrime(crime);
		return "redirect:/crime/";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("id", id);
		return "crime_delete";
		
	}
	@PostMapping("/delete/{id}")
	public String deleteCrime(@PathVariable("id") Integer id) {
		crimeService.deleteCrime(id);
		return "redirect:/crime/";
	}
}
