package com.jacekg.reportSystem.dao;

import java.util.List;

import com.jacekg.reportSystem.entity.FailType;

public interface FailTypeDao {

	List<FailType> getFailTypes();

	FailType getFailTypeById(Integer failTypeId);

}
