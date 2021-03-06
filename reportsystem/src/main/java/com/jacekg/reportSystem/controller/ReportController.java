package com.jacekg.reportSystem.controller;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.multipart.MultipartFile;

import com.jacekg.reportSystem.dto.ReportDto;
import com.jacekg.reportSystem.dto.ReportSummaryDto;
import com.jacekg.reportSystem.dto.SearchReportDto;
import com.jacekg.reportSystem.dto.SelectedReportsDto;
import com.jacekg.reportSystem.dto.ShowReportDto;
import com.jacekg.reportSystem.entity.Image;
import com.jacekg.reportSystem.entity.Report;
import com.jacekg.reportSystem.service.ProductionService;
import com.jacekg.reportSystem.service.ReportService;
import com.jacekg.reportSystem.service.UserService;
import com.jacekg.reportSystem.utilities.Utilities;

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
	private Map<Long, ReportSummaryDto> summaryReportDescriptions;

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

		prodMachines = new LinkedHashMap<Integer, String>();
		prodMachines = Utilities.loadProdMachines(productionService.getProdMachinesWithLines());

		failTypes = new LinkedHashMap<Integer, String>();
		failTypes = Utilities.loadFailTypes(reportService.getFailTypes());

		ReportDto reportDto = new ReportDto();
		reportDto.setDate(LocalDate.now());
		reportDto.setUserId(userId);

		model.addAttribute("prodMachines", prodMachines);
		model.addAttribute("failTypes", failTypes);
		model.addAttribute("reportDto", reportDto);

		return "report/report-form";
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
			prodMachines = Utilities.loadProdMachines(productionService.getProdMachinesWithLines());

			failTypes = new LinkedHashMap<Integer, String>();
			failTypes = Utilities.loadFailTypes(reportService.getFailTypes());

			model.addAttribute("prodMachines", prodMachines);
			model.addAttribute("failTypes", failTypes);

			return "report/report-form";
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
		
		prodMachines = new LinkedHashMap<Integer, String>();
		prodMachines = Utilities.loadProdMachines(productionService.getProdMachinesWithLines());

		for (Report report : reports) {
			reportsList.add(Utilities.mapShowReportDtoToList(report));
		}
		
		model.addAttribute("reportsList", reportsList);
		model.addAttribute("selectedReports", new SelectedReportsDto());
		model.addAttribute("searchReportDto", new SearchReportDto());
		model.addAttribute("prodMachines", prodMachines);
		
		return "report/report-list";
	}
	
	@GetMapping("/showReportsSummary")
	public String showReportsSummary(@ModelAttribute("reportDto") SelectedReportsDto selectedReportsDto, Model model) {
		
		List<Long> selectedReportsId = selectedReportsDto.getSelectedReportsId();
		summaryReportDescriptions = new LinkedHashMap<Long, ReportSummaryDto>();
		
		Report report = null;
		String summaryDescription = null;
		
		if (!selectedReportsId.isEmpty()) {
			for (Long reportId : selectedReportsId) {
				
				report = reportService.getReportWithAllData(reportId);
				
				summaryDescription = report.getProductionLine().getName() 
						+ " " + report.getProductionMachine().getName() 
						+ "<br>" + report.getDescription();
				
				if (!report.getImages().isEmpty()) {
					summaryReportDescriptions.put(
							report.getId(), new ReportSummaryDto(summaryDescription, true));
				} else {
					summaryReportDescriptions.put(
							report.getId(), new ReportSummaryDto(summaryDescription, false));
				}
				
				System.out.println("My logs hashmap data: " + summaryReportDescriptions.get(report.getId()).getIsImage());
			}	
		}
		
		model.addAttribute("summaryReportDescriptions", summaryReportDescriptions);
		
		return "report/report-summary";
	}

	@GetMapping("/showReportDetails")
	public String showReportDetails(@RequestParam("id") Long reportId, Model model) {

		Report report = reportService.getReportWithAllData(reportId);

		ShowReportDto showReportDto = Utilities.mapShowReportDto(report);

		model.addAttribute("showReportDto", showReportDto);

		return "report/report-details";
	}
	
	@PostMapping("/searchReport")
	public String searchReport(@Valid @ModelAttribute("searchReportDto") SearchReportDto searchReportDto,
			BindingResult bindingResult, Model model) {
	
		if (bindingResult.hasErrors()) {
			return "redirect:/report/showReportList";
		}
		
		searchReportDto.dateValidation();
		
		List<Report> searchedReportsList = reportService.searchReports(searchReportDto);
		
		prodMachines = new LinkedHashMap<Integer, String>();
		prodMachines = Utilities.loadProdMachines(productionService.getProdMachinesWithLines());
		
		List<ShowReportDto> reportsList = new ArrayList<ShowReportDto>();
		
		for (Report report : searchedReportsList) {
			reportsList.add(Utilities.mapShowReportDtoToList(report));
		}
		
		model.addAttribute("reportsList", reportsList);
		model.addAttribute("selectedReports", new SelectedReportsDto());
		model.addAttribute("searchReportDto", new SearchReportDto());
		model.addAttribute("prodMachines", prodMachines);
		
		return "report/report-list";
	}

	@GetMapping("/showImage")
	public void showImage(@RequestParam("id") Long reportId, HttpServletResponse response,HttpServletRequest request) 
			throws ServletException, IOException{

		Report report = reportService.getReportWithAllData(reportId);

		ShowReportDto showReportDto = Utilities.mapShowReportDto(report);
		
		byte[] image = showReportDto.getImagesListToShow().get(0).getPicByte();
		byte[] encode = java.util.Base64.getEncoder().encode(showReportDto.getImagesListToShow().get(0).getPicByte());
		
		System.out.println("My logs image byte: " + encode);
		System.out.println("My logs image byte: " + image);
		response.setContentType("image/jpeg, image/jpg, image/png");
		response.getOutputStream().write(image);
		response.getOutputStream().close();
	}
}












