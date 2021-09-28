package com.jacekg.reportSystem.dao;

import com.jacekg.reportSystem.entity.User;

public interface UserDao {

	User findByUserName(String userName);

	void save(User user);

}
