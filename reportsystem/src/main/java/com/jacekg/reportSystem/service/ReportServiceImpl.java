package com.jacekg.reportSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jacekg.reportSystem.dao.FailTypeDao;
import com.jacekg.reportSystem.dao.ReportDao;
import com.jacekg.reportSystem.dao.UserDao;
import com.jacekg.reportSystem.dao.UserDaoImpl;
import com.jacekg.reportSystem.dto.ReportDto;
import com.jacekg.reportSystem.entity.FailType;
import com.jacekg.reportSystem.entity.Report;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private ReportDao reportDao;
	
	@Autowired
	private FailTypeDao failTypeDao;
	
	@Autowired
	private UserDao userDao;
	
	@Override
	@Transactional
	public List<FailType> getFailTypes() {
		return failTypeDao.getFailTypes();
	}

	@Override
	@Transactional
	public void saveReport(ReportDto reportDto) {
		
		Report report = new Report();
		User user = 
		report.setUser(null);
		
		reportDao.saveReport(reportDto);
	}
	
	

}
