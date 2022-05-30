package lt.ku.prison.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lt.ku.prison.classes.PrisonerCrimeKey;
import lt.ku.prison.entities.PrisonerCrime;
import lt.ku.prison.services.CrimeService;
import lt.ku.prison.services.PrisonerCrimeService;
import lt.ku.prison.services.PrisonerService;

@Controller
public class PrisonerCrimeController {
	@Autowired
	PrisonerService prisonerService;
	
	@Autowired
	CrimeService crimeService;
	
	@Autowired
	PrisonerCrimeService prisonerCrimeService;
	
	@GetMapping("/prisoner/newCrime/")
	String home(Model model) {
		model.addAttribute("prisonerCrimeKey", new PrisonerCrimeKey());
		model.addAttribute("prisoners", prisonerService.getAllPrisoners());
		model.addAttribute("crimes", crimeService.getAllCrimes());
		return "prisoner_crime_new";
	}
	
	@PostMapping("/prisoner/newCrime/")
	String addNewPrisonerCrime(
			@Valid
			@ModelAttribute PrisonerCrimeKey prisonerCrimeKey,
			BindingResult result,
			@RequestParam("prisonerId") Integer prisonerId,
			@RequestParam("crimeId") Integer crimeId,
			Model model
			) {
			if(result.hasErrors()) {
				model.addAttribute("prisoners", prisonerService.getAllPrisoners());
				model.addAttribute("crimes", crimeService.getAllCrimes());
				return "prisoner_crime_new";
			}
			
			PrisonerCrime prisonerCrime = new PrisonerCrime();
			prisonerCrimeKey.setCrimeId(crimeId);
			prisonerCrimeKey.setPrisonerId(prisonerId);
			prisonerCrime.setId(prisonerCrimeKey);
			prisonerCrime.setCrime(crimeService.getCrime(crimeId));
			prisonerCrime.setPrisoner(prisonerService.getPrisoner(prisonerId));
			prisonerCrimeService.addPrisonerCrime(prisonerCrime);
			
			return "redirect:/prisoner/";
	}
	
	@GetMapping("/prisoner/crimes/{id}")
	String prisonerCrimeList(Model model, @PathVariable("id") Integer id) {
		model.addAttribute("prisonerCrimes", prisonerCrimeService.getAllCrimesByPrisonerId(id));
		return "prisoner_crime_list";
	}
	
	@PostMapping("/prisoner/crimes/delete")
	String deletePrisonerCrime(@RequestParam("prisonerId") Integer prisonerId,
			@RequestParam("crimeId") Integer crimeId,
			@RequestParam("date") String date
			
			) {
		prisonerCrimeService.deletePrisonerCrime(prisonerId, crimeId, date);
		return "redirect:/prisoner/crimes/" + prisonerId;
	}
}
