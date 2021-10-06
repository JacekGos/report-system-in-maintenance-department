package com.jacekg.reportSystem.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.jacekg.reportSystem.validation.FieldMatch;

@FieldMatch.List({
	@FieldMatch(first = "password", second = "matchingPassword", message = "The password field must match")
})
public class FormChangePassword {
	
	private Long id;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String password;
	
	@NotNull(message = "is required")
	@Size(min = 1, message = "is required")
	private String matchingPassword;
	
	public FormChangePassword() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}
	
}






