package com.jacekg.reportSystem.dto;

public class ReportSummaryDto {
	
	private String description;
	
	private Boolean isImage;
	
	public ReportSummaryDto() {
		
	}

	public ReportSummaryDto(String description, Boolean isImage) {
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

	public Boolean getIsImage() {
		return isImage;
	}

	public void setIsImage(Boolean isImage) {
		this.isImage = isImage;
	}
	
}
