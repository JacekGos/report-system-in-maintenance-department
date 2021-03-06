package com.jacekg.reportSystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.jacekg.reportSystem.dao.FailTypeDao;
import com.jacekg.reportSystem.dao.ProductionLineDao;
import com.jacekg.reportSystem.dao.ProductionMachineDao;
import com.jacekg.reportSystem.dao.ReportDao;
import com.jacekg.reportSystem.dao.UserDao;
import com.jacekg.reportSystem.dto.ReportDto;
import com.jacekg.reportSystem.dto.SearchReportDto;
import com.jacekg.reportSystem.entity.FailType;
import com.jacekg.reportSystem.entity.Image;
import com.jacekg.reportSystem.entity.ProductionLine;
import com.jacekg.reportSystem.entity.ProductionMachine;
import com.jacekg.reportSystem.entity.Report;
import com.jacekg.reportSystem.entity.User;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private ReportDao reportDao;
	
	@Autowired
	private FailTypeDao failTypeDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ProductionMachineDao productionMachineDao;
	
	@Override
	@Transactional
	public List<FailType> getFailTypes() {
		return failTypeDao.getFailTypes();
	}

	@Override
	@Transactional
	public void saveReport(ReportDto reportDto) {
		
		Report report = new Report();
		
		User user = userDao.getUser(reportDto.getUserId());
		
		ProductionMachine productionMachine = productionMachineDao.getProdMachine(reportDto.getProductionMachineId());
		ProductionLine productionLine = productionMachine.getProductionLine();
		
		List<FailType> failTypes = findFailTypes(reportDto.getFailTypes());
		
		MultipartFile[] imagesFromForm = reportDto.getImages();
		List<Image> images = new ArrayList<Image>();
		
		if (imagesFromForm[0].getSize() > 0) {
			System.out.println("My logs images not null");
			images = new ArrayList<Image>();
			
			for (int i = 0; i < imagesFromForm.length; i++) {
				try {
					images.add(new Image(
							imagesFromForm[i].getName(),
							imagesFromForm[i].getContentType(),
							imagesFromForm[i].getBytes()
							));
				}catch (Exception e) {
				}
				
			}
			
			report = new Report(
					user,
					reportDto.getDate(),
					reportDto.getDuration(),
					reportDto.getDescription(),
					productionLine,
					productionMachine,
					images,
					failTypes);
			
			for (Image image : images) {
				image.setReport(report);
			}
		} else {
			
			report = new Report(
					user,
					reportDto.getDate(),
					reportDto.getDuration(),
					reportDto.getDescription(),
					productionLine,
					productionMachine,
					failTypes);
		}
		
		reportDao.saveReport(report);
	}
	
	@Override
	@Transactional
	public List<Report> getReportsToShowList() {
		return reportDao.getReportsToShowList();
	}
	
	@Override
	@Transactional
	public Report getReportWithAllData(Long reportId) {
		
		Report report = reportDao.getReport(reportId);
		
		if (report != null) {
			
			System.out.println(report.getUser());
			System.out.println(report.getProductionLine());
			System.out.println(report.getProductionMachine());
			System.out.println(report.getFailTypes());
			System.out.println(report.getImages());
		}
		
		return report;
	}
	
	private List<FailType> findFailTypes(List<Integer> failTypes) {
		
		List<FailType> failTypeList = new ArrayList<FailType>();
		FailType failType = null;
		
		for (Integer failTypeId : failTypes) {
			
			failType = failTypeDao.getFailTypeById(failTypeId);
			
			failTypeList.add(failType);
		}
		
		return failTypeList;
	}
	
	@Override
	@Transactional
	public List<Report> searchReports(SearchReportDto searchReportDto) {
		
		Long userId = null;
		
		if (searchReportDto.getUserName() != null) {
			
			User user = userDao.findByUserName(searchReportDto.getUserName());
			
			if (user != null) {
				userId = user.getId();
			} 
		}
		
		System.out.println("My logs, userId: " + userId);
		return reportDao.searchReports(searchReportDto, userId);
	}
}
