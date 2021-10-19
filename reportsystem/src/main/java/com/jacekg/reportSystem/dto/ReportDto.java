package com.jacekg.reportSystem.dto;

import java.time.LocalDate;
import java.util.List;

import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ReportDto {
	
	private Long id;
	
	private Long userId;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "wymagane")
	private LocalDate date;
	
	@NotNull(message = "wymagane")
	@Min(value = 0)
	@Max(value = 10000)
	private Integer duration;
	
	@NotNull(message = "wymagane")
	@Size(min = 1, max = 2100, message = "przekroczono ilośc znaków (max 2100)")
	private String description;
	
	private int productionLineId;
	
	@Min(value = 1, message = "wybierz stacje")
	private int productionMachineId;
	
	MultipartFile[] images;
	
	@NotNull(message = "wymagane")
	@Size(min = 1, message = "wybierz rodzaj awarii")
	List<Integer> failTypes;
	
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

	public Integer getDuration() {
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

	public MultipartFile[] getImages() {
		return images;
	}

	public void setImages(MultipartFile[] images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "ReportDto [id=" + id + ", userId=" + userId + ", date=" + date + ", duration=" + duration
				+ ", description=" + description + ", productionLineId=" + productionLineId + ", productionMachineId="
				+ productionMachineId + ", images=" + images + ", failTypes=" + failTypes + "]";
	}
	
	
	
}
