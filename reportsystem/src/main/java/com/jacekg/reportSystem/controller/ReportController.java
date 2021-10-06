package com.jacekg.reportSystem.controller;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jacekg.reportSystem.dto.ReportDto;

@Controller
@RequestMapping("/report")
public class ReportController {
	
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);			
	}
	
	@GetMapping("/showReportForm")
	public String showReportForm(Model model) {
		
		model.addAttribute("reportDto", new ReportDto());
		
		return "report-form";
	}
	
	

}
