package com.jacekg.reportSystem.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jacekg.reportSystem.dto.ReportDto;
import com.jacekg.reportSystem.entity.FailType;

@Repository
public class ReportDaoImpl implements ReportDao {
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveReport(ReportDto reportDto) {
		// TODO Auto-generated method stub
		
	}

}
