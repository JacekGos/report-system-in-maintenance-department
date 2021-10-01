package com.jacekg.reportSystem.service;

import java.util.List;

import javax.validation.Valid;

import com.jacekg.reportSystem.entity.ProductionLine;
import com.jacekg.reportSystem.entity.ProductionMachine;
import com.jacekg.reportSystem.form_entity.FormProductionLine;
import com.jacekg.reportSystem.form_entity.FormProductionMachine;

public interface ProductionService {

	void save(FormProductionLine formProductionLine);
	
	void save(FormProductionMachine formProductionMachine);

	ProductionLine findProdLineByName(String name);

	ProductionMachine findProdMachineByNameAndLine(String name, int lineId);

	List<ProductionLine> getProdLines();

	ProductionLine getProdLine(int lineId);

	ProductionLine getProdLineOnly(int lineId);
}
