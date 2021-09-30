package com.jacekg.reportSystem.dao;

import com.jacekg.reportSystem.entity.ProductionMachine;

public interface ProductionMachineDao {

	ProductionMachine findProdMachineByName(String name);

	void save(ProductionMachine productionMachine);

}
