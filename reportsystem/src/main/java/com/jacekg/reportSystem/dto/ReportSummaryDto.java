package com.jacekg.reportSystem.dto;

public class ReportSummaryDto {
	
	private String description;
	
	private boolean isImage;
	
	public ReportSummaryDto() {
		
	}

	public ReportSummaryDto(String description, boolean isImage) {
		super();
		this.description = description;
		this.isImage = isImage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean getIsImage() {
		return isImage;
	}

	public void setIsImage(boolean isImage) {
		this.isImage = isImage;
	}
	
}
