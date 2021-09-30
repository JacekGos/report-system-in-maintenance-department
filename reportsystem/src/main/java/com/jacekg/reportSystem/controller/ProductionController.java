package com.jacekg.reportSystem.controller;

import java.sql.SQLException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jacekg.reportSystem.entity.ProductionLine;
import com.jacekg.reportSystem.form_entity.FormProductionLine;
import com.jacekg.reportSystem.service.ProductionService;

@Controller
@RequestMapping("/production")
public class ProductionController {
	
	@Autowired
	private ProductionService productionService;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);			
	}
	
	@GetMapping("/showAddProdLineForm")
	public String showAddProdLineForm(Model model) {
		
		model.addAttribute("formProdLine", new FormProductionLine());
		
		return "prod-line-form";
	}
	
	@PostMapping("/processProdLineForm")
	public String processProdLineForm(@Valid @ModelAttribute("formProdLine") FormProductionLine formProductionLine,
			BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "prod-line-form";
		}
		
		ProductionLine productionLine = productionService.findProdLineByName(formProductionLine.getName());
		if (productionLine != null) {
			
			model.addAttribute("formProdLine", new FormProductionLine());
			model.addAttribute("errorMessage", "Podana linia już istnieje");
			
			return "prod-line-form";
		}
		
		try {
			productionService.save(formProductionLine);
		} catch (Exception e) {
			System.out.println("MY log ---> Unique value violated");
			
			String errorMessage = "Coś poszło nie tak";
			
			model.addAttribute("errorMessage", errorMessage);
		}
		
		return "home";
	}
	
	@GetMapping("/showAddProdMachineForm")
	public String showAddProdMachineForm(Model model) {
		
		return "prod-line-form";
	}
	
	

}
