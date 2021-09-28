package com.jacekg.reportSystem.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.jacekg.reportSystem.entity.Role;

@Repository
public class RoleDaoImpl implements RoleDao {

	@Autowired
	@Qualifier("appSessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public Role findRoleByName(String roleName) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Role> query = currentSession.createQuery("FROM Role WHERE name=:roleName");
		query.setParameter("roleName", roleName);
		
		Role role = null;
		
		try {
			role = query.getSingleResult();
		} catch (Exception e) {
			role = null;
		}
		
		return role;
	}
}






