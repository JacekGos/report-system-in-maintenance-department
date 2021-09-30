package com.jacekg.reportSystem.dao;

import com.jacekg.reportSystem.entity.ProductionMachine;

public interface ProductionMachineDao {

	ProductionMachine findProdMachineByNameAndLine(String name, int lineId);

	void save(ProductionMachine productionMachine);

}
