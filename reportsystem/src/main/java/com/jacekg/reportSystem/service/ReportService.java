package com.jacekg.reportSystem.service;

import java.util.List;

import com.jacekg.reportSystem.dto.ReportDto;
import com.jacekg.reportSystem.entity.FailType;

public interface ReportService {

	List<FailType> getFailTypes();

	void saveReport(ReportDto reportDto);

}
