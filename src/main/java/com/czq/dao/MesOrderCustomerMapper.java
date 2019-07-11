package com.czq.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.czq.beans.PageQuery;
import com.czq.dto.SearchOrderDto;
import com.czq.model.MesOrder;

public interface MesOrderCustomerMapper {

	void batchStart(@Param("list")String[] idArray);

	Long getOrderCount();

	int countBySearchDto(@Param("dto") SearchOrderDto dto);

	List<MesOrder> getPageListBySearchDto(@Param("dto") SearchOrderDto dto, @Param("page") PageQuery page);


}
