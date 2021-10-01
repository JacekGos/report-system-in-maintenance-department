package com.jacekg.reportSystem.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jacekg.reportSystem.entity.ProductionLine;
import com.jacekg.reportSystem.entity.ProductionMachine;

@Repository
public class ProductionMachineDaoImpl implements ProductionMachineDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void save(ProductionMachine productionMachine) {
		
		Session currentSession = sessionFactory.getCurrentSession();

		currentSession.saveOrUpdate(productionMachine);
	}
	
	@Override
	public ProductionMachine findProdMachineByNameAndLine(String name, int lineId) {

		Session currentSession = sessionFactory.getCurrentSession();
		
		Query<ProductionMachine> query = 
				currentSession.createQuery("FROM ProductionMachine WHERE name=:name AND prod_line_id=:lineId", 
						ProductionMachine.class);
		query.setParameter("name", name);
		query.setParameter("lineId", lineId);

		ProductionMachine productionMachine = null;

		try {
			productionMachine = query.getSingleResult();
		} catch (Exception e) {
			productionMachine = null;
		}

		return productionMachine;
	}

	@Override
	public ProductionMachine getProdMachine(int machineId) {

		Session currentSession = sessionFactory.getCurrentSession();

		Query<ProductionMachine> query = 
				currentSession.createQuery("FROM ProductionMachine WHERE id=:machineId", ProductionMachine.class);
		query.setParameter("machineId", machineId);

		ProductionMachine productionMachine = null;

		try {
			productionMachine = query.getSingleResult();
		} catch (Exception e) {
			productionMachine = null;
		}

		return productionMachine;
	}

}
