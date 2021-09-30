package com.jacekg.reportSystem.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
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
import org.springframework.web.bind.annotation.RequestParam;

import com.jacekg.reportSystem.entity.ProductionLine;
import com.jacekg.reportSystem.entity.ProductionMachine;
import com.jacekg.reportSystem.form_entity.FormProductionLine;
import com.jacekg.reportSystem.form_entity.FormProductionMachine;
import com.jacekg.reportSystem.service.ProductionService;

@Controller
@RequestMapping("/production")
public class ProductionController {
	
	@Autowired
	private ProductionService productionService;
	
	private Map<Integer, String> prodLines;
	
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
		
		model.addAttribute("formProdMachine", new FormProductionMachine());
		
		prodLines = new LinkedHashMap<Integer, String>();
		prodLines = loadProdLines();
		
		model.addAttribute("prodLines", prodLines);
		
		return "prod-machine-form";
	}
	
	@PostMapping("/processProdMachineForm")
	public String processProdMachineForm(@Valid @ModelAttribute("formProdMachine") FormProductionMachine formProductionMachine,
			BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			
			prodLines = new LinkedHashMap<Integer, String>();
			prodLines = loadProdLines();
			
			model.addAttribute("prodLines", prodLines);
			
			return "prod-machine-form";
		}
		
		ProductionMachine productionMachine = 
				productionService.findProdMachineByNameAndLine(formProductionMachine.getName(), formProductionMachine.getProdLineId());
		
		if (productionMachine != null) {
			
			prodLines = new LinkedHashMap<Integer, String>();
			prodLines = loadProdLines();
			
			model.addAttribute("prodLines", prodLines);
			model.addAttribute("formProdMachine", new FormProductionMachine());
			model.addAttribute("errorMessage", "Podana maszyna już istnieje");
			
			return "prod-machine-form";
		}
		
		try {
			productionService.save(formProductionMachine);
		} catch (Exception e) {
			
			System.out.println("MY log ---> Unique value violated");
			
			String errorMessage = "Coś poszło nie tak";
			
			model.addAttribute("errorMessage", errorMessage);
		}
		
		return "home";
	}
	
	@GetMapping("/showProdLinesList")
	public String showProdLinesList(Model model) {
		
		List<ProductionLine> prodLines = productionService.getProdLines();
		
		model.addAttribute("prodLines", prodLines);
		
		return "prod-lines-list";
	}
	
	@GetMapping("/showProdLineDetails")
	public String showProdLineDetails(@RequestParam("id") int lineId, Model model) {
		
		ProductionLine productionLine = productionService.getProdLine(lineId);
		
		List<ProductionMachine> productionMachines = productionLine.getProductionMachines();
		
		if (!productionMachines.isEmpty()) {
			System.out.println("My log: is not empty");
		}
		
		model.addAttribute("formProdLine", new FormProductionLine());
		model.addAttribute("productionMachines", productionMachines);
		model.addAttribute("productionLine", productionLine);
		
		return "prod-line-details";
	}
	
	private Map<Integer, String> loadProdLines() {

		List<ProductionLine> prodLineList = productionService.getProdLines();

		Map<Integer, String> prodLines = new LinkedHashMap<Integer, String>();

		prodLines = getProdLineNames(prodLineList);

		return prodLines;
	}

	private Map<Integer, String> getProdLineNames(List<ProductionLine> prodLineList) {

		LinkedHashMap<Integer, String> prodLineNames = new LinkedHashMap();

		for (ProductionLine productionLine : prodLineList) {

			prodLineNames.put(productionLine.getId(), productionLine.getName());
		}

		return prodLineNames;
	}

}
