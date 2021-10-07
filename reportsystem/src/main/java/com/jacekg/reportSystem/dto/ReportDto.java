package com.jacekg.reportSystem.dto;

import java.time.LocalDate;
import java.util.List;

import com.jacekg.reportSystem.entity.User;


public class ReportDto {
	
	private Long id;
	
	private Long userId;

	private LocalDate date;
	
	private int duration;
	
	private String description;
	
	private int productionLineId;
	
	private int productionMachineId;
	
	List<Integer> failTypes;
	
	List<Integer> images;
	
	public ReportDto() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getProductionLineId() {
		return productionLineId;
	}

	public void setProductionLineId(int productionLineId) {
		this.productionLineId = productionLineId;
	}

	public int getProductionMachineId() {
		return productionMachineId;
	}

	public void setProductionMachineId(int productionMachineId) {
		this.productionMachineId = productionMachineId;
	}

	public List<Integer> getFailTypes() {
		return failTypes;
	}

	public void setFailTypes(List<Integer> failTypes) {
		this.failTypes = failTypes;
	}

	public List<Integer> getImages() {
		return images;
	}

	public void setImages(List<Integer> images) {
		this.images = images;
	}
	
}
