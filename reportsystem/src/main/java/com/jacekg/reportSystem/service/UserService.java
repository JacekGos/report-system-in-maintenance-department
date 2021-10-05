package com.jacekg.reportSystem.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.jacekg.reportSystem.entity.User;
import com.jacekg.reportSystem.form_entity.FormUser;

public interface UserService extends UserDetailsService {
	
	User findByUserName(String userName);

	List<User> getUsers();

	User getUser(long userId);
	
	Long getUsersAmount(String firstName, String lastName);
		
	void save(FormUser formUser);

	void setUserPassword(long userId, String password);

	void deactivateUser(long userId);

	void activateUser(long userId);

	Long getUserId(String userName);
	
}
