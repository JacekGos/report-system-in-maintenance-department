package com.jacekg.reportSystem.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.jacekg.reportSystem.validation.FieldMatch;

@FieldMatch.List({
	@FieldMatch(first = "password", second = "matchingPassword", message = "podane hasła muszą się zgadzać")
})
public class ChangePasswordDto {
	
	private Long id;
	
	@NotNull(message = "wymagane")
	@Size(max = 30, message = "za długie hasło (max 30 znaków)")
	@Size(min = 5, message = "za krótkie hasło (min 5 znaków)")
	private String password;
	
	@NotNull(message = "wymagane")
	@Size(max = 30, message = "za długie hasło (max 30 znaków)")
	@Size(min = 5, message = "za krótkie hasło (min 5 znaków)")
	private String matchingPassword;
	
	public ChangePasswordDto() {
		
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
		password = password.replaceAll("\\s+", "");
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		matchingPassword = matchingPassword.replaceAll("\\s+", "");
		this.matchingPassword = matchingPassword;
	}
	
}






