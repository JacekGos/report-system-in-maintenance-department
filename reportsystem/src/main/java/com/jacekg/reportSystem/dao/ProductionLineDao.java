package com.jacekg.reportSystem.dao;

import java.util.List;

import com.jacekg.reportSystem.entity.ProductionLine;

public interface ProductionLineDao {

	void save(ProductionLine productionLine);

	ProductionLine findProdLineByName(String name);

	List<ProductionLine> getProdLines();

	ProductionLine findProdLineById(int prodLineId);

	ProductionLine getProdLine(int lineId);

	List<ProductionLine> getProdLinesWithMachines();
}
