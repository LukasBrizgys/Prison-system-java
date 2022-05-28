package lt.ku.prison.controller;

import org.springframework.http.HttpHeaders;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import lt.ku.prison.entities.Prisoner;
import lt.ku.prison.services.CityService;
import lt.ku.prison.services.FileStorageService;
import lt.ku.prison.services.PrisonerService;
import lt.ku.prison.services.StatusService;

@RequestMapping("/prisoner")
@Controller
public class PrisonerController {
	@Autowired
	PrisonerService prisonerService;
	
	@Autowired
	CityService cityService;
	
	@Autowired
	StatusService statusService;
	
	@Autowired
	FileStorageService storageService;
	
	@InitBinder
    public void initBinder(WebDataBinder dataBinder) {

        dataBinder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String value) {
                try {
                    setValue(new SimpleDateFormat("yyyy-MM-dd").parse(value));
                } catch (ParseException e) {
                    setValue(null);
                }
            }
        });

    }
	@GetMapping("/")
	public String prisoners(Model model) {
		model.addAttribute("prisoners", prisonerService.getAllPrisoners());
		return "prisoner_list";
	}
	@GetMapping("/new")
	public String newPrisoner(Model model) {
		model.addAttribute("prisoner", new Prisoner());
		model.addAttribute("cities", cityService.getCities());
		model.addAttribute("statuses", statusService.getAllStatuses());
		
		return "prisoner_new";
	}
	@GetMapping("/delete/{id}")
	public String delete(Model model,
			@PathVariable("id") Integer id) {
		model.addAttribute("id", id);
		return "prisoner_delete";
	}
	@PostMapping("/delete/{id}")
	public String deletePrisoner(@PathVariable("id") Integer id) {
		prisonerService.deletePrisoner(id);
		return "redirect:/prisoner/";
	}
	@PostMapping("/new")
	public String addPrisoner(Model model,
			@Valid
			@ModelAttribute Prisoner prisoner,
			BindingResult result,
			@RequestParam("cityId") Integer cityId,
			@RequestParam("birthDate") String birthDate,
			@RequestParam("statusId") Integer statusId,
			@RequestParam("infoFile") MultipartFile prisonerInfo
			) {
			if(result.hasErrors()) {
			model.addAttribute("cities", cityService.getCities());
			model.addAttribute("statuses", statusService.getAllStatuses());
			return "prisoner_new";
			}
			prisoner.setCity(cityService.getCity(cityId));
			prisoner.setBirthDate(birthDate);
			prisoner.setStatus(statusService.getStatus(statusId));
			prisoner.setFileName(prisonerInfo.getOriginalFilename());
			prisoner = prisonerService.addPrisoner(prisoner);
			storageService.store(prisonerInfo, prisoner.getId().toString());
			
		return "redirect:/prisoner/";
	}
	
	@GetMapping("/update/{id}")
	public String update(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("cities", cityService.getCities());
		model.addAttribute("statuses", statusService.getAllStatuses());
		model.addAttribute("prisoner", prisonerService.getPrisoner(id));
		return "prisoner_update";
	}
	
	
	@PostMapping("/update/{id}")
	public String updatePrisoner(@PathVariable("id") Integer id,
			@ModelAttribute Prisoner prisoner,
			@RequestParam("infoFile") MultipartFile prisonerInfo
			) {
		if(!prisonerInfo.isEmpty()) {
			prisoner.setFileName(prisonerInfo.getOriginalFilename());
			storageService.store(prisonerInfo, prisoner.getId().toString());
		}
			
		prisonerService.updatePrisoner(prisoner);
		return "redirect:/prisoner/";
	}
	
	
	@GetMapping("/info/{id}")
	@ResponseBody
	public ResponseEntity<Resource> getInfo(@PathVariable Integer id){
		Resource file = storageService.loadFile(id.toString());
		Prisoner p = prisonerService.getPrisoner(id);
		
		return ResponseEntity
				.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + p.getFileName()+"\"")
				.body(file);
	}
}
