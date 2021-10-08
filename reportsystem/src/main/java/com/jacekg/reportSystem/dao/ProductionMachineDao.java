package com.jacekg.reportSystem.dao;

import java.util.List;

import com.jacekg.reportSystem.entity.ProductionMachine;

public interface ProductionMachineDao {

	ProductionMachine findProdMachineByNameAndLine(String name, int lineId);

	void save(ProductionMachine productionMachine);

	ProductionMachine getProdMachine(int machineId);

	List<ProductionMachine> getProdMachinesWithLines();
}
