package com.jacekg.reportSystem.form_entity;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class FormProductionLine {
	
	private int id;
	
	@NotNull(message = "wymagane")
	@Size(min = 1, max = 20, message = "za długa/krótka nazwa")
	private String name;
	
	public FormProductionLine() {
		
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
	
}
