package com.jacekg.reportSystem.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "prod_line")
public class ProductionLine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(fetch = FetchType.LAZY,
			mappedBy = "productionLine",
			cascade = {CascadeType.DETACH, CascadeType.MERGE,
					CascadeType.PERSIST, CascadeType.REFRESH})
	private List<ProductionMachine> productionMachines;
	
	@OneToMany(fetch = FetchType.LAZY,
			mappedBy = "productionLine",
			cascade = {CascadeType.DETACH, CascadeType.MERGE,
					CascadeType.PERSIST, CascadeType.REFRESH})
	private List<Report> reports;
	
	public ProductionLine() {
		
	}

	public ProductionLine(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public List<ProductionMachine> getProductionMachines() {
		return productionMachines;
	}

	public void setProductionMachines(List<ProductionMachine> productionMachines) {
		this.productionMachines = productionMachines;
	}

	public List<Report> getReports() {
		return reports;
	}

	public void setReports(List<Report> reports) {
		this.reports = reports;
	}

	public void addProductionMachine(ProductionMachine productionMachine) {
		
		if (productionMachines == null) {
			productionMachines = new ArrayList<>();
		}
		
		productionMachines.add(productionMachine);
		
		productionMachine.setProductionLine(this);
	}
	
	public void addReport(Report report) {
		
		if (reports == null) {
			reports = new ArrayList<>();
		}
		
		reports.add(report);
		
		report.setProductionLine(this);
	}
	
}
