package com.jacekg.reportSystem.dto;

import java.time.LocalDate;

import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


public class SearchReportDto {
	
	private String userName;
	
	private int productionMachineId;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate startDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate endDate;
	
	@Size(min = 1, max = 100, message = "Zbyt długie")
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
	
	public void dateValidation() {
//		if (this.startDate == null || this.endDate == null) {
//			this.startDate = null;
//			this.endDate = null;
//		} else if (this.endDate.isBefore(this.startDate) || this.endDate == null) {
//			this.endDate = startDate;
//		}
		
//		if (this.startDate == null && this.endDate == null) {
//			this.startDate = null;
//			this.endDate = null;
//		} 
//		 if (this.endDate == null && this.startDate != null) {
//			this.endDate = this.startDate;
//		} else if (this.endDate.isBefore(this.startDate) && this.startDate != null) {
//			this.endDate = startDate;
//		}
		
		if (this.startDate != null) {
			if (this.endDate != null && this.endDate.isBefore(this.startDate)) {
				this.endDate = this.startDate;
			} else if (this.endDate == null) {
				this.endDate = this.startDate;
			}
		} else if (this.startDate == null) {
			if (this.endDate != null) {
				this.startDate = this.endDate;
			}
		}
	}
	
}
