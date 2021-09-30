package com.jacekg.reportSystem.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jacekg.reportSystem.entity.ProductionLine;

@Repository
public class ProductionLineDaoImpl implements ProductionLineDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void save(ProductionLine productionLine) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		currentSession.saveOrUpdate(productionLine);
	}

	@Override
	public ProductionLine findProdLineByName(String name) {
		
		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<ProductionLine> query = currentSession.createQuery("FROM ProductionLine WHERE name=:name", ProductionLine.class);
		query.setParameter("name", name);
		
		ProductionLine productionLine = null;
		
		try {
			productionLine = query.getSingleResult();
		} catch (Exception e) {
			productionLine = null;
		}
		
		return productionLine;
	}

}
