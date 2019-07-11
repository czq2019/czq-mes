package com.czq.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.czq.common.JsonData;
import com.czq.param.MesOrderVo;
import com.czq.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	private static String FPATH="order/";
	@Resource
	private OrderService orderService;

	@RequestMapping("/orderBatch.page")
	public String orderBatchPage() {
		return FPATH+"orderBatch";
	}
	@ResponseBody
	@RequestMapping("/insert.json")
	public JsonData insertAjax(MesOrderVo mesOrderVo) {
		orderService.orderBatchInserts(mesOrderVo);//batch 批量
		return JsonData.success();
	}
	
	@ResponseBody
	@RequestMapping("/update.json")
	public JsonData updateOrder(MesOrderVo mesOrderVo) {
    	orderService.update(mesOrderVo);
    	return JsonData.success();
    }
	
//	 @RequestMapping("/order.json")
//	    @ResponseBody
//	    public JsonData searchPage(SearchOrderParam param, PageQuery page) {
//	    	PageResult<MesOrder> pr=(PageResult<MesOrder>) orderService.searchPageList(param, page);
//	    	return JsonData.success(pr);
//	    }
}
