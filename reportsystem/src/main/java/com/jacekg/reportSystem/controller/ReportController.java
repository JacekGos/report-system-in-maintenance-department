package com.jacekg.reportSystem.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jacekg.reportSystem.dto.ReportDto;
import com.jacekg.reportSystem.entity.FailType;
import com.jacekg.reportSystem.entity.ProductionLine;
import com.jacekg.reportSystem.service.ProductionService;
import com.jacekg.reportSystem.service.ReportService;

@Controller
@RequestMapping("/report")
public class ReportController {

	@Autowired
	private ProductionService productionService;
	
	@Autowired
	private ReportService reportService;

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

		model.addAttribute("prodLines", prodLines);
		model.addAttribute("reportDto", new ReportDto());


		return "report-form";
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

	private Map<Integer, String> loadFailTypes() {

		List<FailType> failTypeList = reportService.getFailTypes();

		Map<Integer, String> failTypes = new LinkedHashMap<Integer, String>();

		prodLines = getFailTypeNames(failTypeList);

		return prodLines;
	}

	private Map<Integer, String> getFailTypeNames(List<FailType> failTypeList) {

		LinkedHashMap<Integer, String> failTypeNames = new LinkedHashMap();

		for (FailType failType : failTypeList) {

			failTypeNames.put(failType.getId(), failType.getName());
		}

		return failTypeNames;

	}



}












