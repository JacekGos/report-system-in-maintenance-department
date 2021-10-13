package com.jacekg.reportSystem.dto;

import java.util.List;

public class SelectedReportsDto {
	
	private List<Long> selectedReportsId;
	
	public SelectedReportsDto() {
		
	}

	public List<Long> getSelectedReportsId() {
		return selectedReportsId;
	}

	public void setSelectedReportsId(List<Long> selectedReportsId) {
		this.selectedReportsId = selectedReportsId;
	}

}
