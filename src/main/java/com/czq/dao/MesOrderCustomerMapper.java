package com.czq.dao;

import org.apache.ibatis.annotations.Param;

public interface MesOrderCustomerMapper {

	void batchStart(@Param("list")String[] idArray);

	Long getOrderCount();

}
