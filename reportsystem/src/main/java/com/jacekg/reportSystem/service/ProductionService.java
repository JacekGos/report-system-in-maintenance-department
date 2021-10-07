package com.jacekg.reportSystem.service;

import java.util.List;

import javax.validation.Valid;

import com.jacekg.reportSystem.dto.ProductionLineDto;
import com.jacekg.reportSystem.dto.ProductionMachineDto;
import com.jacekg.reportSystem.entity.ProductionLine;
import com.jacekg.reportSystem.entity.ProductionMachine;

public interface ProductionService {

	void save(ProductionLineDto formProductionLine);
	
	void save(ProductionMachineDto formProductionMachine);

	ProductionLine findProdLineByName(String name);

	ProductionMachine findProdMachineByNameAndLine(String name, int lineId);

	List<ProductionLine> getProdLines();

	ProductionLine getProdLine(int lineId);

	ProductionLine getProdLineOnly(int lineId);

	ProductionMachine getProdMachine(int machineId);

	List<ProductionLine> getProdLinesWithMachines();

	ProductionLine getProdLineWithMachines(int prodLineId);
}
