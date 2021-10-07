package com.jacekg.reportSystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jacekg.reportSystem.dao.FailTypeDao;
import com.jacekg.reportSystem.dao.ReportDao;
import com.jacekg.reportSystem.entity.FailType;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private ReportDao reportDao;
	
	@Autowired
	private FailTypeDao failTypeDao;
	
	@Override
	@Transactional
	public List<FailType> getFailTypes() {
		return failTypeDao.getFailTypes();
	}
	
	

}
