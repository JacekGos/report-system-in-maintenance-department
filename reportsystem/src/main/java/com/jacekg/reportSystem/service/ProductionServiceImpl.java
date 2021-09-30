package com.jacekg.reportSystem.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacekg.reportSystem.dao.ProductionLineDao;
import com.jacekg.reportSystem.entity.ProductionLine;
import com.jacekg.reportSystem.form_entity.FormProductionLine;

@Service
public class ProductionServiceImpl implements ProductionService {
	
	@Autowired
	private ProductionLineDao productionLineDao;
	
	@Override
	@Transactional
	public void save(FormProductionLine formProductionLine) {
		
		ProductionLine productionLine = new ProductionLine();
		
		productionLine.setName(formProductionLine.getName());
		
		productionLineDao.save(productionLine);
	}

	@Override
	@Transactional
	public ProductionLine findProdLineByName(String name) {
		return productionLineDao.findProdLineByName(name);
	}
	
	
	

}
