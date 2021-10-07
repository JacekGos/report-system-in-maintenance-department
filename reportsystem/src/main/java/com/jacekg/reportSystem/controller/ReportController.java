package com.jacekg.reportSystem.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jacekg.reportSystem.dto.ReportDto;
import com.jacekg.reportSystem.entity.ProductionLine;
import com.jacekg.reportSystem.service.ProductionService;

@Controller
@RequestMapping("/report")
public class ReportController {
	
	@Autowired
	private ProductionService productionService;
	
	private Map<Integer, String> prodLines;
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);			
	}
	
	@GetMapping("/showReportForm")
	public String showReportForm(Model model) {
		
		
		prodLines = new LinkedHashMap<Integer, String>();
		prodLines = loadProdLines();
		
		
		model.addAttribute("reportDto", new ReportDto());
		
		
		
		return "report-form";
	}

	private Map<Integer, String> loadProdLines() {
		
		List<ProductionLine> prodLineList = productionService.getProdLines();

		Map<Integer, String> prodLines = new LinkedHashMap<Integer, String>();

		prodLines = getProdLineNames(prodLineList);

		return prodLines;
	}
	
	

}
