package com.jacekg.reportSystem.dto;

import java.util.List;

public class SelectedReportsDto {
	
	private List<Long> selectedReportsId;
	
	private String summaryDescription;
	
	public SelectedReportsDto() {
		
	}

	public List<Long> getSelectedReportsId() {
		return selectedReportsId;
	}

	public void setSelectedReportsId(List<Long> selectedReportsId) {
		this.selectedReportsId = selectedReportsId;
	}

	public String getSummaryDescription() {
		return summaryDescription;
	}

	public void setSummaryDescription(String summaryDescription) {
		this.summaryDescription = summaryDescription;
	}
}
