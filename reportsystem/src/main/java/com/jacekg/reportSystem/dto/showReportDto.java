package com.jacekg.reportSystem.dto;

import java.time.LocalDate;
import java.util.List;

public class showReportDto {
	
	private Long id;
	
	private String userName;
	
	private String productionLineName;
	
	private String productionMachineName;
	
	private LocalDate date;
	
	private Integer duration;

	private String description;
	
	List<Integer> images;

	List<Integer> failTypes;
	
	public showReportDto() {
		
	}
	
	public showReportDto(Long id, String userName, String productionLineName, String productionMachineName,
			LocalDate date, Integer duration, String description, List<Integer> images, List<Integer> failTypes) {
		this.id = id;
		this.userName = userName;
		this.productionLineName = productionLineName;
		this.productionMachineName = productionMachineName;
		this.date = date;
		this.duration = duration;
		this.description = description;
		this.images = images;
		this.failTypes = failTypes;
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

	public List<Integer> getImages() {
		return images;
	}

	public List<Integer> getFailTypes() {
		return failTypes;
	}
	
	

}
