package com.jacekg.reportSystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jacekg.reportSystem.dao.FailTypeDao;
import com.jacekg.reportSystem.dao.ProductionLineDao;
import com.jacekg.reportSystem.dao.ProductionMachineDao;
import com.jacekg.reportSystem.dao.ReportDao;
import com.jacekg.reportSystem.dao.UserDao;
import com.jacekg.reportSystem.dao.UserDaoImpl;
import com.jacekg.reportSystem.dto.ReportDto;
import com.jacekg.reportSystem.entity.FailType;
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
	
	@Autowired
	private ProductionLineDao productionLineDao;
	
	@Override
	@Transactional
	public List<FailType> getFailTypes() {
		return failTypeDao.getFailTypes();
	}

	@Override
	@Transactional
	public void saveReport(ReportDto reportDto) {
		
		
		User user = userDao.getUser(reportDto.getUserId());
		ProductionMachine productionMachine = productionMachineDao.getProdMachine(reportDto.getProductionMachineId());
		ProductionLine productionLine = productionMachine.getProductionLine();
		List<FailType> failTypes = findFailTypes(reportDto.getFailTypes());
		
		//TODO saving images and add to report
		
		Report report = new Report(
				user,
				reportDto.getDate(),
				reportDto.getDuration(),
				reportDto.getDescription(),
				productionLine,
				productionMachine,
				failTypes);
		
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

}
