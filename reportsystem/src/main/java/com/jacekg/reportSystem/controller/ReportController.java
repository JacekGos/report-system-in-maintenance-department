package com.jacekg.reportSystem.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.Deflater;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jacekg.reportSystem.dto.ReportDto;
import com.jacekg.reportSystem.dto.ShowReportDto;
import com.jacekg.reportSystem.dto.ShowReportDto2;
import com.jacekg.reportSystem.entity.FailType;
import com.jacekg.reportSystem.entity.ProductionMachine;
import com.jacekg.reportSystem.entity.Report;
import com.jacekg.reportSystem.service.ProductionService;
import com.jacekg.reportSystem.service.ReportService;
import com.jacekg.reportSystem.service.UserService;

@Controller
@RequestMapping("/report")
public class ReportController {

	@Autowired
	private ProductionService productionService;
	
	@Autowired
	private ReportService reportService;
	
	@Autowired
	private UserService userService;

	private Map<Integer, String> prodMachines;
	private Map<Integer, String> failTypes;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);	
	}

	@GetMapping("/showReportForm")
	public String showReportForm(Model model, Principal principal) {
		
		Long userId = 0L;
		
		if (principal != null) {
			String userName = principal.getName();
			userId = userService.getUserId(userName);
		}
		
//		String userName = principal.getName();
		
		prodMachines = new LinkedHashMap<Integer, String>();
		prodMachines = loadProdMachines();
		
		failTypes = new LinkedHashMap<Integer, String>();
		failTypes = loadFailTypes();
		
		ReportDto reportDto = new ReportDto();
		reportDto.setDate(LocalDate.now());
		reportDto.setUserId(userId);
			
		model.addAttribute("prodMachines", prodMachines);
		model.addAttribute("failTypes", failTypes);
		model.addAttribute("reportDto", reportDto);

		return "report-form";
	}
	
	@PostMapping("/processReportForm")
	public String processReportForm(@Valid @ModelAttribute("reportDto") ReportDto reportDto,
			BindingResult bindingResult, @ModelAttribute("message") String message, Model model) throws IOException {
		
		MultipartFile[] images = reportDto.getImages();
		
		System.out.println("My logs: getContentType " +  images[0].getContentType());
		System.out.println("My logs: getOriginalFilename " +  images[0].getOriginalFilename());
		System.out.println("My logs: getSize " +  images[0].getSize());
		
		
		
		if (bindingResult.hasErrors()) {
			
			prodMachines = new LinkedHashMap<Integer, String>();
			prodMachines = loadProdMachines();
			
			failTypes = new LinkedHashMap<Integer, String>();
			failTypes = loadFailTypes();
			
			model.addAttribute("prodMachines", prodMachines);
			model.addAttribute("failTypes", failTypes);
			
			return "report-form";
		}
		
		try {
			reportService.saveReport(reportDto);
		} catch (Exception e) {
			return "redirect:/report/showReportForm";
		}
		
		return "redirect:/report/showReportForm";
	}
	
	@GetMapping("/showReportList")
	public String showReportList(Model model) {
		
		List<Report> reports = reportService.getReportsToShowList();
		
		List<ShowReportDto> reportsList = new ArrayList<ShowReportDto>();
		
		for (Report report : reports) {
			reportsList.add(mapShowReportDtoToList(report));
		}
		
		model.addAttribute("reportsList", reportsList);
		
		return "report-list";
	}
	
	@GetMapping("/showReportDetails")
	public String showReportDetails(@RequestParam("id") Long reportId, Model model) {
		
		Report report = reportService.getReportWithAllData(reportId);
		
		ShowReportDto showReportDto = mapShowReportDto(report);
		System.out.println("My logs: " + showReportDto.toString());
		model.addAttribute("showReportDto", showReportDto);
		
		return "report-details";
	}
	
	private Map<Integer, String> loadProdMachines() {
		
		List<ProductionMachine> prodMachineList = productionService.getProdMachinesWithLines();

		Map<Integer, String> prodMachines = new LinkedHashMap<Integer, String>();

		prodMachines = getProdMachineNames(prodMachineList);

		return prodMachines;
	}

	private Map<Integer, String> getProdMachineNames(List<ProductionMachine> prodMachineList) {
		
		Map<Integer, String> prodMachineNames = new LinkedHashMap<Integer, String>();

		for (ProductionMachine productionMachine : prodMachineList) {

			prodMachineNames.put(
					productionMachine.getId(), 
					productionMachine.getProductionLine().getName() + "-" + productionMachine.getName());
		}

		return prodMachineNames;
	}
	
	private Map<Integer, String> loadFailTypes() {

		List<FailType> failTypeList = reportService.getFailTypes();

		Map<Integer, String> failTypes = new LinkedHashMap<Integer, String>();

		failTypes = getFailTypeNames(failTypeList);

		return failTypes;
	}

	private Map<Integer, String> getFailTypeNames(List<FailType> failTypeList) {

		LinkedHashMap<Integer, String> failTypeNames = new LinkedHashMap<Integer, String>();

		for (FailType failType : failTypeList) {

			failTypeNames.put(failType.getId(), failType.getName());
		}

		return failTypeNames;

	}

	private ShowReportDto mapShowReportDto(Report report) {

		ShowReportDto showReportDto = new ShowReportDto(
				report.getId(),
				report.getUser().getUserName(),
				report.getProductionLine().getName(),
				report.getProductionMachine().getName(),
				report.getDate(),
				report.getDuration(),
				report.getDescription(),
				report.getImagesNames(),
				report.getFailTypesNames());
		
		return showReportDto;
	}
	
	private ShowReportDto mapShowReportDtoToList(Report report) {

		ShowReportDto showReportDto = new ShowReportDto(
				report.getId(),
				report.getUser().getUserName(),
				report.getProductionLine().getName(),
				report.getProductionMachine().getName(),
				report.getDate());
		
		return showReportDto;
	}
	
}












