package com.czq.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.czq.beans.PageQuery;
import com.czq.dto.ProductDto;
import com.czq.dto.SearchProductDto;
import com.czq.model.MesOrder;
import com.czq.model.MesProduct;

public interface MesProductCustomerMapper {

	int countBySearchDto(@Param("dto")SearchProductDto dto);
	List<ProductDto> getPageListBySearchDto(@Param("dto")SearchProductDto dto, @Param("page")PageQuery page);
	
}
