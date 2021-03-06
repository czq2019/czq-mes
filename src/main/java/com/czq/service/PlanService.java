package com.czq.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.czq.beans.PageQuery;
import com.czq.beans.PageResult;
import com.czq.dao.MesOrderCustomerMapper;
import com.czq.dao.MesOrderMapper;
import com.czq.dao.MesPlanCustomerMapper;
import com.czq.dao.MesPlanMapper;
import com.czq.dao.MesProductMapper;
import com.czq.dto.SearchPlanDto;
import com.czq.exception.ParamException;
import com.czq.model.MesOrder;
import com.czq.model.MesPlan;
import com.czq.model.MesProduct;
import com.czq.param.MesPlanVo;
import com.czq.param.SearchPlanParam;
import com.czq.util.BeanValidator;
import com.czq.util.MyStringUtils;
import com.czq.util.UUIDUtil;
import com.google.common.base.Preconditions;


@Service
public class PlanService {
	@Resource
	private MesOrderMapper mesOrderMapper;
	@Resource
	private MesOrderCustomerMapper mesOrderCustomerMapper;
	@Resource
	private MesPlanMapper mesPlanMapper;
	@Resource
	private MesPlanCustomerMapper mesPlanCustomerMapper;
	@Resource
	private MesProductMapper mesProductMapper;
	@Resource
	private SqlSession sqlSession;
	
	public void startPlansByOrderIds(String[] ids) {
		for(String tempId:ids) {
			Integer id=Integer.parseInt(tempId);
			MesOrder order=mesOrderMapper.selectByPrimaryKey(id);
			Preconditions.checkNotNull(order);
			prePlan(order);
		}
	}
	

	//创建为启动计划
	public void prePlan(MesOrder mesOrder) {
		// 批量处理
		MesPlanMapper planMapper = sqlSession.getMapper(MesPlanMapper.class);
		MesPlan mesPlan =MesPlan.builder().planOrderid(mesOrder.getOrderId()).planProductname(mesOrder.getOrderProductname())//
				.planClientname(mesOrder.getOrderClientname()).planContractid(mesOrder.getOrderContractid()).planImgid(mesOrder.getOrderImgid())//
				.planMaterialname(mesOrder.getOrderMaterialname()).planCurrentstatus("计划").planCurrentremark("计划待执行").planSalestatus(mesOrder.getOrderSalestatus())//
				.planMaterialsource(mesOrder.getOrderMaterialsource()).planHurrystatus(mesOrder.getOrderHurrystatus()).planStatus(0).planCometime(mesOrder.getOrderCometime())//
				.planCommittime(mesOrder.getOrderCommittime()).planInventorystatus(mesOrder.getOrderInventorystatus()).build();
		mesPlan.setPlanOperator("user01");
		mesPlan.setPlanOperateIp("127.0.0.1");
		mesPlan.setPlanOperateTime(new Date());
		planMapper.insertSelective(mesPlan);
	}
	
	//启动计划
		public void batchStartWithIds(String ids) {
			if(ids!=null&&ids.length()>0) {
				//批量处理
				MesPlanMapper mapper=sqlSession.getMapper(MesPlanMapper.class);
				//考虑到需要判断一下id是否为空,执行自定义update语句
				//批处理
				String[] strs=ids.split(",");
				String[] idsTemp=strs[0].split("&");
				String[] datesTemp=strs[1].split("&");
				String startTime=datesTemp[0];
				String endTime=datesTemp[1];
				for(int i=0;i<idsTemp.length;i++) {
					MesPlan mesPlan=new MesPlan();
					mesPlan.setId(Integer.parseInt(idsTemp[i]));
					mesPlan.setPlanWorkstarttime(MyStringUtils.string2Date(startTime,null));
					mesPlan.setPlanWorkendtime(MyStringUtils.string2Date(endTime,null));
					mesPlan.setPlanStatus(1);
					mesPlan.setPlanCurrentremark("计划已启动");
					mapper.updateByPrimaryKeySelective(mesPlan);
					
					//半成品材料 生成
					MesPlan plan=mesPlanMapper.selectByPrimaryKey(Integer.parseInt(idsTemp[i]));
					//产生半成品材料
					String orderid=plan.getPlanOrderid();
					MesOrder order=mesOrderCustomerMapper.selectByOrderId(orderid);
					//product
					MesProduct mesProduct=MesProduct.builder().productId(UUIDUtil.generateUUID())//
							.productOrderid(order.getId()).productPlanid(plan.getId())//
							.productMaterialname(order.getOrderMaterialname())//
							.productImgid(order.getOrderImgid())//
							.productMaterialsource(order.getOrderMaterialsource())//\
							.productStatus(0).build();
					mesProduct.setProductOperateIp("127.0.0.1");
					mesProduct.setProductOperator("user01");
					mesProduct.setProductOperateTime(new Date());
					mesProductMapper.insertSelective(mesProduct);
				}
			}
		}
	public PageResult<MesPlan> searchPageList(SearchPlanParam param, PageQuery page) {
		BeanValidator.check(page);
		SearchPlanDto dto = new SearchPlanDto();
		// copyparamto
		if (StringUtils.isNotBlank(param.getKeyword())) {
			dto.setKeyword("%" + param.getKeyword() + "%");
		}
		if (StringUtils.isNotBlank(param.getSearch_msource())) {
			dto.setSearch_msource(param.getSearch_msource());
		}
		if (StringUtils.isNotBlank(param.getSearch_status())) {
			dto.setSearch_status(Integer.parseInt(param.getSearch_status()));
		}
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			if (StringUtils.isNotBlank(param.getFromTime())) {
				dto.setFromTime(dateFormat.parse(param.getFromTime()));
			}
			if (StringUtils.isNotBlank(param.getToTime())) {
				dto.setToTime(dateFormat.parse(param.getToTime()));
			}
		} catch (Exception e) {
			throw new ParamException("日期格式不对，yyyy-MM-dd");
		}
		int count = mesPlanCustomerMapper.countBySearchDto(dto);
		if (count > 0) {
			List<MesPlan> planList = mesPlanCustomerMapper.getPageListBySearchDto(dto, page);
			return PageResult.<MesPlan>builder().total(count).data(planList).build();
		}
		return PageResult.<MesPlan>builder().build();
	}
/*
  
 */
	public void update(MesPlanVo mesPlanVo) {
		BeanValidator.check(mesPlanVo);
		MesPlan mesPlan=new MesPlan();
		BeanUtils.copyProperties(mesPlanVo, mesPlan);
		mesPlan.setPlanCometime(MyStringUtils.string2Date(mesPlanVo.getPlanCometime(),null));
		mesPlan.setPlanCommittime(MyStringUtils.string2Date(mesPlanVo.getPlanCommittime(),null));
		mesPlan.setPlanWorkstarttime(MyStringUtils.string2Date(mesPlanVo.getPlanWorkstarttime(),null));
		mesPlan.setPlanWorkendtime(MyStringUtils.string2Date(mesPlanVo.getPlanWorkendtime(),null));
		mesPlan.setPlanOperator("user01");
		mesPlan.setPlanOperateIp("127.0.0.1");
		mesPlan.setPlanOperateTime(new Date());
		mesPlanMapper.updateByPrimaryKeySelective(mesPlan);
	}

}
