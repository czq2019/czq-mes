package com.czq.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.czq.beans.PageQuery;
import com.czq.beans.PageResult;
import com.czq.common.JsonData;
import com.czq.common.SameUrlData;
import com.czq.dto.ProductDto;
import com.czq.model.MesProduct;
import com.czq.param.MesProductVo;
import com.czq.param.MesProductVo1;
import com.czq.param.SearchProductParam;
import com.czq.service.ProductService;
@Controller
@RequestMapping("/product")
public class ProductController {

	private static String FPATH="product";
	
	@Resource
	private ProductService productService;
	
	//澧炲姞鏉愭枡椤甸潰
	@RequestMapping("/productinsert.page")
	public String productInsertPage() {
		return FPATH+"/productinsert";
	}
	/*
	//澧炲姞鏉愭枡鍔熻兘
	@RequestMapping("/insert.json")
	@SameUrlData//闃叉閲嶅鎻愪氦
	public String insert(MesProductVo productVo) {
		productService.insert(productVo);
		return FPATH+"/product";
	}*/
	
	
	//鏉愭枡绠＄悊椤甸潰
	@RequestMapping("/product.page")
	public String productList() {
		return FPATH+"/product";
	}
	
	//鏉愭枡鍒板簱绠＄悊鍒嗛〉椤甸潰
	@RequestMapping("/product.json")
	@ResponseBody
    public JsonData searchPage(SearchProductParam param, PageQuery page) {
    	PageResult<ProductDto> pr=(PageResult<ProductDto>) productService.searchPageList(param, page);
    	return JsonData.success(pr);
    }
	
/*	//鏉愭枡鎵归噺鍚姩
	@RequestMapping("/productBatchStart.json")
    public String productBatchStart(String ids) {
		productService.batchStart(ids);
		return FPATH+"/product";
    }*/
	
	//鏉愭枡鍒板簱绠＄悊
	@RequestMapping("/productCome.page")
	public String productComePage() {
		return FPATH+"/productCome";
	}
	
	//閽㈤敪鏌ヨ
	@RequestMapping("/productIron.page")
	public String productIronList() {
		return FPATH+"/productIron";
	}
	
	//缁戝畾鐘舵�佸垪琛�
	@RequestMapping("/productBindList.page")
	public String productBindList() {
		return FPATH+"/productBindList";
	}
	
	//缁戝畾閽㈡潗鍒嗛〉鏄剧ず
//	@RequestMapping("/productBindList.json")
//	@ResponseBody
//    public JsonData searchBindListPage(SearchProductParam param, PageQuery page) {
//    	PageResult<ProductDto> pr=(PageResult<ProductDto>) productService.searchPageBindList(param, page);
//    	return JsonData.success(pr);
//    }

	
}