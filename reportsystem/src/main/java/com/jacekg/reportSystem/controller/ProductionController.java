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
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jacekg.reportSystem.dto.ProductionLineDto;
import com.jacekg.reportSystem.dto.ProductionMachineDto;
import com.jacekg.reportSystem.entity.ProductionLine;
import com.jacekg.reportSystem.entity.ProductionMachine;
import com.jacekg.reportSystem.service.ProductionService;
import com.jacekg.reportSystem.utilities.Utilities;

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
		
		model.addAttribute("productionLineDto", new ProductionLineDto());
		
		return "production/prod-line-form";
	}
	
	@GetMapping("/showUpdateProdLineForm")
	public String showUpdateProdLineForm(@RequestParam("id") int lineId, Model model) {
		
		ProductionLine productionLine = productionService.getProdLineOnly(lineId);
		
		ProductionLineDto productionLineDto = new ProductionLineDto();
		productionLineDto.setId(lineId);
		productionLineDto.setName(productionLine.getName());
		
		model.addAttribute("productionLineDto", productionLineDto);
		
		return "production/prod-line-form";
	}
	
	@PostMapping("/processProdLineForm")
	public String processProdLineForm(@Valid @ModelAttribute("productionLineDto") ProductionLineDto productionLineDto,
			BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			return "production/prod-line-form";
		}
		
		ProductionLine productionLine = productionService.findProdLineByName(productionLineDto.getName());
		
		int formProdLineId = productionLineDto.getId();
		
		if (productionLine != null && productionLine.getId() != formProdLineId) {
			
			model.addAttribute("productionLineDto", productionLineDto);
			model.addAttribute("errorMessage", "Podana linia już istnieje");
			
			return "production/prod-line-form";
		}
		
		try {
			productionService.save(productionLineDto);
		} catch (Exception e) {
			System.out.println("MY log ---> Unique value violated");
			
			String errorMessage = "Coś poszło nie tak";
			
			model.addAttribute("errorMessage", errorMessage);
		}
		
		return "navigation/home";
	}
	
	@GetMapping("/showAddProdMachineForm")
	public String showAddProdMachineForm(Model model) {
		
		model.addAttribute("productionMachineDto", new ProductionMachineDto());
		
		prodLines = new LinkedHashMap<Integer, String>();
		prodLines = Utilities.loadProdLines(productionService.getProdLines());
		
		model.addAttribute("prodLines", prodLines);
		
		return "production/prod-machine-form";
	}
	
	@GetMapping("/showUpdateProdMachineForm")
	public String showUpdateProdMachineForm(@RequestParam("id") int machineId, Model model) {
		
		ProductionMachine productionMachine = productionService.getProdMachine(machineId);
		
		ProductionMachineDto productionMachineDto = new ProductionMachineDto();
		productionMachineDto.setId(machineId);
		productionMachineDto.setName(productionMachine.getName());
		productionMachineDto.setProdLineName(productionMachine.getProductionLine().getName());
		
		System.out.println("prodLineName: " + productionMachineDto.getProdLineName());
		
		prodLines = new LinkedHashMap<Integer, String>();
		prodLines = Utilities.loadProdLines(productionService.getProdLines());
		
		model.addAttribute("prodLines", prodLines);
		model.addAttribute("prodLineName", productionMachineDto.getProdLineName());
		model.addAttribute("productionMachineDto", productionMachineDto);
		
		return "production/prod-machine-form";
	}
	
	@PostMapping("/processProdMachineForm")
	public String processProdMachineForm(@Valid @ModelAttribute("productionMachineDto") ProductionMachineDto productionMachineDto,
			BindingResult bindingResult, Model model) {
		
		if (bindingResult.hasErrors()) {
			
			prodLines = new LinkedHashMap<Integer, String>();
			prodLines = Utilities.loadProdLines(productionService.getProdLines());
			
			model.addAttribute("prodLines", prodLines);
			
			return "production/prod-machine-form";
		}
		
		ProductionMachine productionMachine = 
				productionService.findProdMachineByNameAndLine(
						productionMachineDto.getName(), 
						productionMachineDto.getProdLineId());
		
		int formProdMachineId = productionMachineDto.getId();
		
		if (productionMachine != null && productionMachine.getId() != formProdMachineId) {
			
			prodLines = new LinkedHashMap<Integer, String>();
			prodLines = Utilities.loadProdLines(productionService.getProdLines());
			
			model.addAttribute("prodLines", prodLines);
			model.addAttribute("productionMachineDto", productionMachineDto);
			model.addAttribute("prodLineName", productionMachineDto.getProdLineName());
			model.addAttribute("errorMessage", "Podana maszyna już istnieje");
			
			return "production/prod-machine-form";
		}
		
		try {
			productionService.save(productionMachineDto);
		} catch (Exception e) {
			
			System.out.println("MY log ---> Unique value violated");
			
			String errorMessage = "Coś poszło nie tak";
			
			model.addAttribute("errorMessage", errorMessage);
		}
		
		return "navigation/home";
	}
	
	@GetMapping("/showProdLinesList")
	public String showProdLinesList(Model model) {
		
		List<ProductionLine> prodLines = productionService.getProdLines();
		
		model.addAttribute("prodLines", prodLines);
		
		return "production/prod-lines-list";
	}
	
	@GetMapping("/showProdLineDetails")
	public String showProdLineDetails(@RequestParam("id") int lineId, Model model) {
		
		ProductionLine productionLine = productionService.getProdLine(lineId);
		
		List<ProductionMachine> productionMachines = productionLine.getProductionMachines();
		
		model.addAttribute("prodLineId", lineId);		
		model.addAttribute("productionMachines", productionMachines);
		model.addAttribute("productionLine", productionLine);
		
		return "production/prod-line-details";
	}
}




