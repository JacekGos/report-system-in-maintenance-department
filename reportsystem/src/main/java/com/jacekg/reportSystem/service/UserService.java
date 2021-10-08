package com.jacekg.reportSystem.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.jacekg.reportSystem.dto.UserDto;
import com.jacekg.reportSystem.entity.User;

public interface UserService extends UserDetailsService {
	
	User findByUserName(String userName);

	List<User> getUsers();

	User getUserWithRoles(long userId);
	
	Long getUsersAmount(String firstName, String lastName);
		
	void save(UserDto formUser);

	void setUserPassword(long userId, String password);

	void deactivateUser(long userId);

	void activateUser(long userId);

	Long getUserId(String userName);
	
}
