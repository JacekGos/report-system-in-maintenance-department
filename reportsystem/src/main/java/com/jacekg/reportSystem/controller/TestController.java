package com.jacekg.reportSystem.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.jacekg.reportSystem.entity.ProductionLine;
import com.jacekg.reportSystem.entity.ProductionMachine;
import com.jacekg.reportSystem.service.ProductionService;

@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private ProductionService productionService;
	
	private Map<Integer, String> prodMachines;
	
	@GetMapping("/loadProdMachines")
	public String loadProdMachines() {
		
		int prodLineId = 1;
		
		ProductionLine productionLine = productionService.getProdLineWithMachines(prodLineId);
		List<ProductionMachine> productionMachines = productionLine.getProductionMachines();
		
		prodMachines = new LinkedHashMap<Integer, String>();
		
		for (ProductionMachine productionMachine : productionMachines) {

			prodMachines.put(productionMachine.getId(), productionMachine.getName());
		}
		
		String productionMachinesList = new Gson().toJson(prodMachines);
		
		return productionMachinesList;
	}

}
