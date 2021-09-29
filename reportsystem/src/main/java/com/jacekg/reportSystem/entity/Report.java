package com.jacekg.reportSystem.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "report")
public class Report {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	private User user;
	
	@Column(name = "date")
	private LocalDate date;
	
	@Column(name = "duration")
	private int duration;
	
	@Column(name = "description")
	private String description;
	
	private ProductionLine productionLine;
	
	private ProductionMachine productionMachine;
	
	private List<Image> images;
	
	private List<FailType> failTypes;
	
	public Report() {
		
	}

	public Report(User user, LocalDate date, int duration, String description, ProductionLine productionLine,
			ProductionMachine productionMachine, List<FailType> failTypes) {
		this.user = user;
		this.date = date;
		this.duration = duration;
		this.description = description;
		this.productionLine = productionLine;
		this.productionMachine = productionMachine;
		this.failTypes = failTypes;
	}

	public Report(User user, LocalDate date, int duration, String description, ProductionLine productionLine,
			ProductionMachine productionMachine, List<Image> images, List<FailType> failTypes) {
		super();
		this.user = user;
		this.date = date;
		this.duration = duration;
		this.description = description;
		this.productionLine = productionLine;
		this.productionMachine = productionMachine;
		this.images = images;
		this.failTypes = failTypes;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public ProductionLine getProductionLine() {
		return productionLine;
	}

	public void setProductionLine(ProductionLine productionLine) {
		this.productionLine = productionLine;
	}

	public ProductionMachine getProductionMachine() {
		return productionMachine;
	}

	public void setProductionMachine(ProductionMachine productionMachine) {
		this.productionMachine = productionMachine;
	}

	public List<Image> getImages() {
		return images;
	}

	public void setImages(List<Image> images) {
		this.images = images;
	}

	public List<FailType> getFailTypes() {
		return failTypes;
	}

	public void setFailTypes(List<FailType> failTypes) {
		this.failTypes = failTypes;
	}
	
}






