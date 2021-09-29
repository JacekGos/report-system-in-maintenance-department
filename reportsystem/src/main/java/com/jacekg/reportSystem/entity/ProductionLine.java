package com.jacekg.reportSystem.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "prod_line")
public class ProductionLine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	private List<ProductionMachine> productionMachines;
	
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
	
	private void addProductionMachine(ProductionMachine productionMachine) {
		
		if (productionMachines == null) {
			productionMachines = new ArrayList<>();
		}
		
		productionMachines.add(productionMachine);
		
		productionMachine.setProductionLine(this);
	}
	
}
