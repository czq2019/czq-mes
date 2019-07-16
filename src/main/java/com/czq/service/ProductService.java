package com.czq.service;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import com.czq.beans.PageQuery;
import com.czq.beans.PageResult;
import com.czq.dao.MesOrderMapper;
import com.czq.dao.MesProductCustomerMapper;
import com.czq.dao.MesProductMapper;
import com.czq.dto.ProductDto;
import com.czq.dto.SearchProductDto;
import com.czq.param.SearchProductParam;
import com.czq.util.BeanValidator;


@Service
public class ProductService {

	@Resource
	private MesProductMapper mesProdcutMapper;
	@Resource
	private MesOrderMapper mesOrderMapper; 
	@Resource
	private MesProductCustomerMapper mesProductCustomerMapper;

	@Resource
	private SqlSession sqlSession;
	// 到库分页
		public PageResult<ProductDto> searchPageList(SearchProductParam param, PageQuery page) {
			// 校验
			BeanValidator.check(page);
			// vo-dto
			SearchProductDto dto = new SearchProductDto();

			if (StringUtils.isNotBlank(param.getKeyword())) {
				dto.setKeyword("%" + param.getKeyword() + "%");
			}
			if (StringUtils.isNotBlank(param.getSearch_source())) {
				dto.setSearch_source(param.getSearch_source());
				;
			}
			if (param.getSearch_status() != null) {
				dto.setSearch_status(param.getSearch_status());
			}
			int count = mesProductCustomerMapper.countBySearchDto(dto);
			if (count > 0) {
				List<ProductDto> productList = mesProductCustomerMapper.getPageListBySearchDto(dto, page);
				return PageResult.<ProductDto>builder().total(count).data(productList).build();
			}

			return PageResult.<ProductDto>builder().build();
		}
	

}
