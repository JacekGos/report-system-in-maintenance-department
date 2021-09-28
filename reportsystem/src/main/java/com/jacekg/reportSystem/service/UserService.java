package com.jacekg.reportSystem.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.jacekg.reportSystem.entity.User;

public interface UserService extends UserDetailsService {
	
	User findByUserName(String userName);
		
//	void save(FormUser formUser);
	
}
