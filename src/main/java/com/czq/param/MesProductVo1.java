package com.czq.param;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class MesProductVo1 {
	private Integer counts=1;
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