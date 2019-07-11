package com.czq.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.czq.beans.PageQuery;
import com.czq.beans.PageResult;
import com.czq.common.JsonData;
import com.czq.model.MesOrder;
import com.czq.param.MesOrderVo;
import com.czq.param.SearchOrderParam;
import com.czq.service.OrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
	
	private static String FPATH="order/";
	@Resource
	private OrderService orderService;
	//批量处理转发
	@RequestMapping("/orderBatch.page")
	public String orderBatchPage() {
		return FPATH+"orderBatch";
	}
	
	@RequestMapping("/order.page")
	public String orderPage() {
		return FPATH+"order";
	}

	@ResponseBody
	@RequestMapping("/insert.json")
	public JsonData insertAjax(MesOrderVo mesOrderVo) {
		orderService.orderBatchInserts(mesOrderVo);
		return JsonData.success();
	}
	
	@ResponseBody
	@RequestMapping("/update.json")
	public JsonData updateOrder(MesOrderVo mesOrderVo) {
    	orderService.update(mesOrderVo);
    	return JsonData.success();
    }
	
	 @RequestMapping("/order.json")
	    @ResponseBody
	    public JsonData searchPage(SearchOrderParam param, PageQuery page) {
	    	PageResult<MesOrder> pr=(PageResult<MesOrder>) orderService.searchPageList(param, page);
	    	return JsonData.success(pr);
	 }
	
		
	//批量启动处理
		@ResponseBody
		@RequestMapping("/orderBatchStart.json")
		public JsonData orderBatchStart(String ids) {
			orderService.batchStart(ids);
			return JsonData.success();
		}
}
