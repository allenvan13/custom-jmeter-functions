package com.model;

import com.sun.istack.internal.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author makejava
 * @since 2022-12-02 13:39:04
 */
@Data
public class PurchasePlanDto implements Serializable {

    private Long id;

    private String planName;

    private Long projectId;

    private String projectName;
    /**
     * 预计签约金额,元
     */
    private BigDecimal estimatedAmount;
    /**
     * 采购类别id
     */
    private Long purchaseCategoryId;
    /**
     * 采购类别名称
     */
    private String purchaseCategoryName;
    /**
     * 采购方式编码
     */
    private String purchaseMethodCode;
    /**
     * 采购方式名称
     */
    private String purchaseMethodName;
    /**
     * 供应商类别编码
     */
    private String providerCategoryId;
    /**
     * 供应商类别名称
     */
    private String providerCategoryName;
    /**
     * 入场时间
     */

    private Date recordTime;
    /**
     * 计划开始时间
     */
    private Date planStartTime;
    /**
     * 计划完成时间
     */
    private Date planFinishTime;
    /**
     * 经办人id
     */
    private Long managerUserId;
    /**
     * 经办人账号
     */
    private String managerUserCode;
    /**
     * 经办人姓名
     */
    private String managerUserName;
    /**
     * 议标名称
     */
    private String bidTypeName;
    /**
     * 议标值
     */
    private String bidTypeValue;
    /**
     * 是否临时采购计划,1是0否
     */
    private Boolean temporaryPlan;
    /**
     * 是否补标,1是0否
     */
    private Boolean supplementBid;
    /**
     * 用于战采协议
     */
    private Boolean ifStrategic;
    /**
     * 是否启用2n+1控制
     */
    private Boolean ifTn;
    /**
     * 是否启用预计签约金额控制
     */
    private Boolean ifSignMoneyControl;
    /**
     * 技术标评标人员是否奇数
     */
    private Boolean ifEvaluationStaffOdd;
    /**
     * 技术标评标人员是否大于等于3
     */
    private Boolean ifEvaluationStaffGteThree;
    /**
     * 是否控制价下限控制
     */
    private Boolean ifControlPriceFloor;
    /**
     * 节点list
     */
    private List<PlanNodeDto> nodes;

}