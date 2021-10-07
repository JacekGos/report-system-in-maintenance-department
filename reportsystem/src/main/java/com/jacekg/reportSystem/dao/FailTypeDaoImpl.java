package com.jacekg.reportSystem.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jacekg.reportSystem.entity.FailType;

@Repository
public class FailTypeDaoImpl implements FailTypeDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<FailType> getFailTypes() {

		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<FailType> query = currentSession.createQuery("FROM FailType", FailType.class);
		
		return query.getResultList();
	}

}
