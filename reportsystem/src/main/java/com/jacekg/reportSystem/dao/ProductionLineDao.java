package com.jacekg.reportSystem.dao;

import com.jacekg.reportSystem.entity.ProductionLine;

public interface ProductionLineDao {

	void save(ProductionLine productionLine);

	ProductionLine findProdLineByName(String name);

}
