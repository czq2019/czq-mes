package com.czq.dto;

import java.util.Date;

import com.czq.model.MesOrder;
import com.czq.model.MesPlan;
import com.czq.model.MesProduct;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
	//order
	private MesOrder order;
	//plan
	private MesPlan plan;
	//parent
	private MesProduct parent;
	
    private Integer id;

    private Integer pId;

    private String productId;

    private Integer productOrderid;

    private Integer productPlanid;

    private Float productTargetweight;

    private Float productRealweight;

    private Float productLeftweight;

    private Float productBakweight;

    private String productIrontype;

    private Float productIrontypeweight;

    private String productMaterialname;

    private String productImgid;

    private String productMaterialsource;

    private Integer productStatus;

    private String productRemark;

    private String productOperator;

    private Date productOperateTime;

    private String productOperateIp;

	
}
