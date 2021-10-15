package com.jacekg.reportSystem.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.jacekg.reportSystem.entity.Image;

public class ShowReportDto {
	
	private Long id;
	
	private String userName;
	
	private String productionLineName;
	
	private String productionMachineName;
	
	private LocalDate date;
	
	private Integer duration;

	private String description;
	
	MultipartFile[] images;
	
	List<Image> imagesListToShow;
	
	List<String> imagesNames;

	List<String> failTypesNames;
	
	boolean isImage;
	
	public ShowReportDto() {
		
	}
	
	public ShowReportDto(Long id, String userName, String productionLineName, String productionMachineName,
			LocalDate date, Integer duration, String description, List<Image> images,
			List<String> failTypesNames, boolean isImage) {
		this.id = id;
		this.userName = userName;
		this.productionLineName = productionLineName;
		this.productionMachineName = productionMachineName;
		this.date = date;
		this.duration = duration;
		this.description = description;
		this.imagesListToShow = images;
		this.failTypesNames = failTypesNames;
		this.isImage = isImage;
	}
	
	public ShowReportDto(Long id, String userName, String productionLineName, String productionMachineName,
			LocalDate date) {
		super();
		this.id = id;
		this.userName = userName;
		this.productionLineName = productionLineName;
		this.productionMachineName = productionMachineName;
		this.date = date;
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
	
	public MultipartFile[] getImages() {
		return images;
	}

	public void setImages(MultipartFile[] images) {
		this.images = images;
	}
	
	public List<Image> getImagesListToShow() {
		return imagesListToShow;
	}

	public void setImagesListToShow(List<Image> imagesListToShow) {
		this.imagesListToShow = imagesListToShow;
	}

	public List<String> getImagesNames() {
		return imagesNames;
	}

	public List<String> getFailTypesNames() {
		return failTypesNames;
	}
	
	public boolean getIsImage() {
		return isImage;
	}

	public void setIsImage(boolean isImage) {
		this.isImage = isImage;
	}

	@Override
	public String toString() {
		return "ShowReportDto [id=" + id + ", userName=" + userName + ", productionLineName=" + productionLineName
				+ ", productionMachineName=" + productionMachineName + ", date=" + date + ", duration=" + duration
				+ ", description=" + description + ", imagesNames=" + imagesNames + ", failTypesNames=" + failTypesNames
				+ "]";
	}


}
