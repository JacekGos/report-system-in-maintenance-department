package com.jacekg.reportSystem.service;

import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jacekg.reportSystem.dao.ProductionLineDao;
import com.jacekg.reportSystem.dao.ProductionMachineDao;
import com.jacekg.reportSystem.entity.ProductionLine;
import com.jacekg.reportSystem.entity.ProductionMachine;
import com.jacekg.reportSystem.form_entity.FormProductionLine;
import com.jacekg.reportSystem.form_entity.FormProductionMachine;

@Service
public class ProductionServiceImpl implements ProductionService {
	
	@Autowired
	private ProductionLineDao productionLineDao;
	
	@Autowired
	private ProductionMachineDao productionMachineDao;
	
	@Override
	@Transactional
	public void save(FormProductionLine formProductionLine) {
		
		ProductionLine productionLine = new ProductionLine();
		
		productionLine.setName(formProductionLine.getName());
		
		productionLineDao.save(productionLine);
	}
	
	@Override
	@Transactional
	public void save(FormProductionMachine formProductionMachine) {
		
		ProductionMachine productionMachine = new ProductionMachine();
		ProductionLine productionLine = productionLineDao.findProdLineById(formProductionMachine.getProdLineId());
		
		productionMachine.setName(formProductionMachine.getName());

		if (productionLine != null) {
			
			productionMachine.setProductionLine(productionLine);
			productionLine.addProductionMachine(productionMachine);
		}
		
		productionMachineDao.save(productionMachine);
		productionLineDao.save(productionLine);
	}
	
	@Override
	@Transactional
	public ProductionLine findProdLineByName(String name) {
		return productionLineDao.findProdLineByName(name);
	}

	@Override
	@Transactional
	public ProductionMachine findProdMachineByNameAndLine(String name, int lineId) {
		return productionMachineDao.findProdMachineByNameAndLine(name, lineId);
	}

	@Override
	@Transactional
	public List<ProductionLine> getProdLines() {
		
		List<ProductionLine> productionLines = productionLineDao.getProdLines();
		
		return productionLines;
	}

	@Override
	@Transactional
	public ProductionLine getProdLine(int lineId) {
		
		ProductionLine productionLine = productionLineDao.getProdLine(lineId);
		
		productionLine.getProductionMachines().get(0);
				
		return productionLine;
	}
}
