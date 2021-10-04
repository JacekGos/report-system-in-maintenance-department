package com.jacekg.reportSystem.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
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
	
	@GetMapping("/showUpdateProdLineForm")
	public String showUpdateProdLineForm(@RequestParam("id") int lineId, Model model) {
		
		ProductionLine productionLine = productionService.getProdLineOnly(lineId);
		
		FormProductionLine formProductionLine = new FormProductionLine();
		formProductionLine.setId(lineId);
		formProductionLine.setName(productionLine.getName());
		
		model.addAttribute("formProdLine", formProductionLine);
		
		return "prod-line-form";
	}
	
	@PostMapping("/processProdLineForm")
	public String processProdLineForm(@Valid @ModelAttribute("formProdLine") FormProductionLine formProductionLine,
			BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "prod-line-form";
		}
		
		ProductionLine productionLine = productionService.findProdLineByName(formProductionLine.getName());
		
		int formProdLineId = formProductionLine.getId();
		
		if (productionLine != null && productionLine.getId() != formProdLineId) {
			
			model.addAttribute("formProdLine", formProductionLine);
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
	
	@GetMapping("/showUpdateProdMachineForm")
	public String showUpdateProdMachineForm(@RequestParam("id") int machineId, Model model) {
		
		ProductionMachine productionMachine = productionService.getProdMachine(machineId);
		
		FormProductionMachine formProductionMachine = new FormProductionMachine();
		formProductionMachine.setId(machineId);
		formProductionMachine.setName(productionMachine.getName());
		formProductionMachine.setProdLineName(productionMachine.getProductionLine().getName());
		
		System.out.println("prodLineName: " + formProductionMachine.getProdLineName());
		
		prodLines = new LinkedHashMap<Integer, String>();
		prodLines = loadProdLines();
		
		model.addAttribute("prodLines", prodLines);
		model.addAttribute("prodLineName", formProductionMachine.getProdLineName());
		model.addAttribute("formProdMachine", formProductionMachine);
		
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
				productionService.findProdMachineByNameAndLine(
						formProductionMachine.getName(), 
						formProductionMachine.getProdLineId());
		
		int formProdMachineId = formProductionMachine.getId();
		
		if (productionMachine != null && productionMachine.getId() != formProdMachineId) {
			
			prodLines = new LinkedHashMap<Integer, String>();
			prodLines = loadProdLines();
			
			model.addAttribute("prodLines", prodLines);
			model.addAttribute("formProdMachine", formProductionMachine);
			model.addAttribute("prodLineName", formProductionMachine.getProdLineName());
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
		
		model.addAttribute("prodLineId", lineId);		
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
