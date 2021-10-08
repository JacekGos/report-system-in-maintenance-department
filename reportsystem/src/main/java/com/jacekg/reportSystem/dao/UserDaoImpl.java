package com.jacekg.reportSystem.dao;

import java.util.List;

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

		}
		
		return user;
	}

	@Override
	public void save(User user) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(user);
	}

	@Override
	public List<User> getUsers() {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<User> query = 
				currentSession.createQuery("SELECT DISTINCT u FROM User u"
						+ " JOIN FETCH u.roles ORDER BY u.id", User.class);
		
		return query.getResultList();
	}

	@Override
	public User getUserWithRoles(long userId) {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<User> query = 
				currentSession.createQuery("SELECT DISTINCT u FROM User u"
						+ " JOIN FETCH u.roles WHERE u.id=:userId", User.class);
		query.setParameter("userId", userId);

		User user = null;

		try {
			user = query.getSingleResult();
		} catch (Exception e) {
			user = null;
		}

		return user;
	}

	@Override
	public Long getUsersAmount(String firstName, String lastName) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Long> query = 
				currentSession.createQuery("SELECT count(u) FROM User u WHERE "
						+ "firstName=:firstName AND lastName=:lastName", Long.class);
		query.setParameter("firstName", firstName);
		query.setParameter("lastName", lastName);
		
		Long usersAmount = query.getSingleResult();
		
		return usersAmount;
	}

	@Override
	public Long getUserId(String userName) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<Long> query = 
				currentSession.createQuery("SELECT id FROM User WHERE userName=:userName", Long.class);
		query.setParameter("userName", userName);
		
		Long userId = null;
		
		try {
			userId = query.getSingleResult();
		} catch (Exception e) {
			userId = null;
		}
		
		return userId;
	}

	@Override
	public User getUser(Long userId) {
		
		Session currentSession = sessionFactory.getCurrentSession();

		Query<User> query = 
				currentSession.createQuery("FROM User WHERE id=:userId", User.class);
		query.setParameter("userId", userId);

		User user = null;

		try {
			user = query.getSingleResult();
		} catch (Exception e) {
			user = null;
		}

		return user;
	}

}
