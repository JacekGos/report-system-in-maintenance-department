package com.jacekg.reportSystem.dao;

import com.jacekg.reportSystem.entity.Role;

public interface RoleDao {
	
	public Role findRoleByName(String roleName);
}
