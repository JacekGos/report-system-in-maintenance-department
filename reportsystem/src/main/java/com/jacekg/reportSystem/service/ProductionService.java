package com.jacekg.reportSystem.service;

import com.jacekg.reportSystem.entity.ProductionLine;
import com.jacekg.reportSystem.form_entity.FormProductionLine;

public interface ProductionService {

	void save(FormProductionLine formProductionLine);

	ProductionLine findProdLineByName(String name);

}
