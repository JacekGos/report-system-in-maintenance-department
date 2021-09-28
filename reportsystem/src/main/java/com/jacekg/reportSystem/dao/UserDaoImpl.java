package com.jacekg.reportSystem.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.jacekg.reportSystem.entity.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	@Qualifier("appSessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public User findByUserName(String userName) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<User> query = currentSession.createQuery("FROM User WHERE username=:userName", User.class);
		query.setParameter("userName", userName);
		
		User user = null;
		try {
			
			user = query.getSingleResult();
		} catch (Exception e) {
			user = null;
		}
		
		return user;
	}

	@Override
	public void save(User user) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(user);
	}

}
