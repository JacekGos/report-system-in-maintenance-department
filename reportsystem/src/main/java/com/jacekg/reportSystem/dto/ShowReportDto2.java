package com.jacekg.reportSystem.dto;

import org.springframework.web.multipart.MultipartFile;


public class ShowReportDto2 {
	
	MultipartFile image;
	
	String image2;
	
	public ShowReportDto2() {
		
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	
	

}
