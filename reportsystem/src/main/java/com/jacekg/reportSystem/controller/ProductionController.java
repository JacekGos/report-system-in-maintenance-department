package com.jacekg.reportSystem.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.jacekg.reportSystem.form_entity.FormProductionLine;

@Controller
@RequestMapping("/production")
public class ProductionController {
	
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
		
		return "home";
	}
	
	@GetMapping("/showAddProdMachineForm")
	public String showAddProdMachineForm(Model model) {
		
		return "prod-line-form";
	}
	
	

}
