package com.jacekg.reportSystem.dto;

import org.springframework.web.multipart.MultipartFile;


public class ShowReportDto2 {
	
	MultipartFile image;
	
	String name;
	
	public ShowReportDto2() {
		
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
