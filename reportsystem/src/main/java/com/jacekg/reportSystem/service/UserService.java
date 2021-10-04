package com.jacekg.reportSystem.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.jacekg.reportSystem.entity.User;
import com.jacekg.reportSystem.form_entity.FormUser;

public interface UserService extends UserDetailsService {
	
	User findByUserName(String userName);

	List<User> getUsers();

	User getUser(long userId);
	
	User getUser(String userName);
		
	void save(FormUser formUser);
	
}
