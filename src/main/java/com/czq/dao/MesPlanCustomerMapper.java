package com.czq.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.czq.beans.PageQuery;
import com.czq.dto.SearchPlanDto;
import com.czq.model.MesPlan;

public interface MesPlanCustomerMapper {

	int countBySearchDto(@Param("dto") SearchPlanDto dto);

	List<MesPlan> getPageListBySearchDto(@Param("dto") SearchPlanDto dto, @Param("page") PageQuery page);
    
}