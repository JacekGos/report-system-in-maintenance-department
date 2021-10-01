package com.jacekg.reportSystem.dao;

import java.util.List;

import com.jacekg.reportSystem.entity.User;

public interface UserDao {

	User findByUserName(String userName);

	void save(User user);

	List<User> getUsers();

}
