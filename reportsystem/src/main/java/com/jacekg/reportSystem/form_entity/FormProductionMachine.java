package com.jacekg.reportSystem.form_entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class FormProductionMachine {
	
	private int id;
	
	@NotNull(message = "wymagane")
	@Size(min = 1, max = 20, message = "za długa/krótka nazwa")
	private String name;
	
	private int prodLineId;
	
	public FormProductionMachine() {
		
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

	public int getProdLineId() {
		return prodLineId;
	}

	public void setProdLineId(int prodLineId) {
		this.prodLineId = prodLineId;
	}
	
}
