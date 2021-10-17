package com.jacekg.reportSystem.dao;

import java.util.List;

import com.jacekg.reportSystem.dto.ReportDto;
import com.jacekg.reportSystem.dto.SearchReportDto;
import com.jacekg.reportSystem.entity.Report;

public interface ReportDao {

	void saveReport(Report report);

	List<Report> getReportsToShowList();

	Report getReport(Long reportId);

	List<Report> searchReports(SearchReportDto searchReportDto, Long userId);
}
