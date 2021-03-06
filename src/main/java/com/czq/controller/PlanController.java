package com.czq.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.czq.beans.PageQuery;
import com.czq.beans.PageResult;
import com.czq.common.JsonData;
import com.czq.model.MesPlan;
import com.czq.param.MesPlanVo;
import com.czq.param.SearchPlanParam;
import com.czq.service.PlanService;




@Controller
@RequestMapping("/plan")
public class PlanController {

	private static String FPATH="plan/";
	
	@Resource
	private PlanService planService;
	
	@RequestMapping("/plan.page")
	public String planPage() {
		return FPATH+"plan";
	}
	@RequestMapping("/planStarted.page")
	public String planStartedPage() {
		return FPATH+"planStarted";
	}
	//批量处理待执行计划启动
		//批量启动处理
		@ResponseBody
		@RequestMapping("/planBatchStart.json")
		public JsonData planBatchStart(String ids) {
			planService.batchStartWithIds(ids);
			return JsonData.success();
		}
		
		//分页显示
	    @RequestMapping("/plan.json")
	    @ResponseBody
	    public JsonData searchPage(SearchPlanParam param, PageQuery page) {
	    	PageResult<MesPlan> pr=(PageResult<MesPlan>) planService.searchPageList(param, page);
	    	return JsonData.success(pr);
	    }
	    
	    @RequestMapping("/update.json")
	    @ResponseBody
	    public JsonData updatePlan(MesPlanVo mesPlanVo) {
	    	planService.update(mesPlanVo);
	    	return JsonData.success();
	    }
}
