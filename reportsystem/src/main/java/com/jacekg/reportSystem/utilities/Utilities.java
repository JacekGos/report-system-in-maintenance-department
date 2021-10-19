package com.jacekg.reportSystem.utilities;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.jacekg.reportSystem.dto.ShowReportDto;
import com.jacekg.reportSystem.dto.UserDto;
import com.jacekg.reportSystem.entity.FailType;
import com.jacekg.reportSystem.entity.Image;
import com.jacekg.reportSystem.entity.ProductionLine;
import com.jacekg.reportSystem.entity.ProductionMachine;
import com.jacekg.reportSystem.entity.Report;
import com.jacekg.reportSystem.entity.User;
import com.jacekg.reportSystem.service.ProductionService;

public final class Utilities {
	
	private Utilities() {
		
	}
	
	public static Map<Integer, String> loadProdLines(List<ProductionLine> prodLineList) {

//		List<ProductionLine> prodLineList = productionService.getProdLines();

		Map<Integer, String> prodLines = new LinkedHashMap<Integer, String>();

		prodLines = getProdLineNames(prodLineList);

		return prodLines;
	}
	
	private static Map<Integer, String> getProdLineNames(List<ProductionLine> prodLineList) {

		LinkedHashMap<Integer, String> prodLineNames = new LinkedHashMap();

		for (ProductionLine productionLine : prodLineList) {

			prodLineNames.put(productionLine.getId(), productionLine.getName());
		}

		return prodLineNames;
	}
	
	public static Map<Integer, String> loadProdMachines(List<ProductionMachine> prodMachineList) {

		Map<Integer, String> prodMachines = new LinkedHashMap<Integer, String>();

		prodMachines = getProdMachineNames(prodMachineList);

		return prodMachines;
	}

	private static Map<Integer, String> getProdMachineNames(List<ProductionMachine> prodMachineList) {

		Map<Integer, String> prodMachineNames = new LinkedHashMap<Integer, String>();
		prodMachineNames.put(-1, "Wybierz");
		
		for (ProductionMachine productionMachine : prodMachineList) {

			prodMachineNames.put(
					productionMachine.getId(), 
					productionMachine.getProductionLine().getName() + "-" + productionMachine.getName());
		}

		return prodMachineNames;
	}
	
	public static Map<Integer, String> loadFailTypes(List<FailType> failTypeList) {

		Map<Integer, String> failTypes = new LinkedHashMap<Integer, String>();

		failTypes = getFailTypeNames(failTypeList);

		return failTypes;
	}

	private static Map<Integer, String> getFailTypeNames(List<FailType> failTypeList) {

		LinkedHashMap<Integer, String> failTypeNames = new LinkedHashMap<Integer, String>();

		for (FailType failType : failTypeList) {

			failTypeNames.put(failType.getId(), failType.getName());
		}

		return failTypeNames;

	}
	
	public static ShowReportDto mapShowReportDto(Report report) {
		
		List<Image> images = report.getImages();
		
		boolean isImage = !images.isEmpty();
		
		ShowReportDto showReportDto = new ShowReportDto(
				report.getId(),
				report.getUser().getUserName(),
				report.getProductionLine().getName(),
				report.getProductionMachine().getName(),
				report.getDate(),
				report.getDuration(),
				report.getDescription(),
				images,
				report.getFailTypesNames(),
				isImage);

		return showReportDto;
	}

	public static ShowReportDto mapShowReportDtoToList(Report report) {

		ShowReportDto showReportDto = new ShowReportDto(
				report.getId(),
				report.getUser().getUserName(),
				report.getProductionLine().getName(),
				report.getProductionMachine().getName(),
				report.getDate());

		return showReportDto;
	}
	
	public static UserDto fillUserDto(User user) {

		UserDto userDto = new UserDto();
		userDto.setId(user.getId());
		userDto.setFirstName(user.getFirstName());
		userDto.setLastName(user.getLastName());
		userDto.setUserName(user.getUserName());
		userDto.setPassword(user.getPassword());
		userDto.setEmail(user.getEmail());
		userDto.setRole(user.getRoleName());
		userDto.setEnabled(user.isEnabled());
		userDto.setCredentialsNonExpired(user.isCredentialsNonExpired());
		userDto.setNonExpired(user.isNonExpired());
		userDto.setNonLocked(user.isNonLocked());

		return userDto;
	}
}




















