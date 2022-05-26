package lt.ku.prison.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lt.ku.prison.entities.Role;
import lt.ku.prison.entities.User;
import lt.ku.prison.services.CityService;
import lt.ku.prison.services.RoleService;
import lt.ku.prison.services.UserService;

@Controller
public class RegisterController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	CityService cityService;
	
	@Autowired
	RoleService roleService;
	
	@RequestMapping("/register")
	public String home(Model model) {
		model.addAttribute("cities", cityService.getCities());
		model.addAttribute("user", new User());
		return "register";
	}
	
	@PostMapping("/register")
	public String newRegistration(
			@Valid
			@ModelAttribute User user,
			BindingResult result,
			@RequestParam("cityId") Integer cityId,
			@RequestParam("password") String password,
			@RequestParam("passwordRepeat") String passwordRepeat,
			Model model
			) {
		if(!password.equals(passwordRepeat)) result.rejectValue("passwordRepeat","error.user.passwordRepeat", "Slaptažodžiai nesutampa");
		
		if(result.hasErrors()) {
			model.addAttribute("cities", cityService.getCities());
			return "register";
		}
		Role role = roleService.getRoleByName("Prižiūrėtojas");
		user.setRole(role);
		user.setCity(cityService.getCity(cityId));
		userService.addUser(user);
		return "redirect:/";
	}
}
