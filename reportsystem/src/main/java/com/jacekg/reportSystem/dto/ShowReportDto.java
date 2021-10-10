package com.jacekg.reportSystem.dto;

import java.time.LocalDate;
import java.util.List;

public class ShowReportDto {
	
	private Long id;
	
	private String userName;
	
	private String productionLineName;
	
	private String productionMachineName;
	
	private LocalDate date;
	
	private Integer duration;

	private String description;
	
	List<String> imagesNames;

	List<String> failTypesNames;
	
	public ShowReportDto() {
		
	}
	
	public ShowReportDto(Long id, String userName, String productionLineName, String productionMachineName,
			LocalDate date, Integer duration, String description, List<String> images, List<String> failTypesNames) {
		this.id = id;
		this.userName = userName;
		this.productionLineName = productionLineName;
		this.productionMachineName = productionMachineName;
		this.date = date;
		this.duration = duration;
		this.description = description;
		this.imagesNames = images;
		this.failTypesNames = failTypesNames;
	}

	public Long getId() {
		return id;
	}

	public String getUserName() {
		return userName;
	}

	public String getProductionLineName() {
		return productionLineName;
	}

	public String getProductionMachineName() {
		return productionMachineName;
	}

	public LocalDate getDate() {
		return date;
	}

	public Integer getDuration() {
		return duration;
	}

	public String getDescription() {
		return description;
	}

	public List<String> getImagesNames() {
		return imagesNames;
	}

	public List<String> getFailTypesNames() {
		return failTypesNames;
	}
	
	

}
