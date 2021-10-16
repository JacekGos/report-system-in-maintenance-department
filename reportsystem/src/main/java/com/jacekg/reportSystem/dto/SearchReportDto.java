package com.jacekg.reportSystem.dto;

import java.time.LocalDate;

public class SearchReportDto {
	
	private String userName;
	
	private int productionMachineId;
	
	private LocalDate startDate;
	
	private LocalDate endDate;

	private String keyWord;
	
	public SearchReportDto() {
		
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getProductionMachineId() {
		return productionMachineId;
	}

	public void setProductionMachineId(int productionMachineId) {
		this.productionMachineId = productionMachineId;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}
	
}
