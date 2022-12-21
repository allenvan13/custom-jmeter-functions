package com.model;

import com.sun.istack.internal.NotNull;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author makejava
 * @since 2022-12-02 11:41:37
 */
@Data
public class PlanNodeDto implements Serializable {

    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 计划id
     */
    private Long planId;
    /**
     * 采购关联方式编码
     */
    private String purchaseMethodCode;
    /**
     * 采购关联方式节点编码
     */
    private String purchaseMethodNodeCode;
    /**
     * 计划开始时间
     */
    private Date startTime;
    /**
     * 计划完成时间
     */
    private Date finishTime;
    /**
     * 实际完成时间
     */
    private Date realFinishTime;
    /**
     * 负责人id
     */
    private Long chargeUserId;
    /**
     * 负责人账号
     */
    private String chargeUserCode;
    /**
     * 负责人姓名
     */
    private String chargeUserName;
    /**
     * 责任部门
     */
    private String chargeOrg;
    /**
     * 责任部门值
     */
    private String chargeOrgValue;
    /**
     * 是否必要节点
     */
    private Boolean ifNecessity;
    /**
     * 排序
     */
    private Integer sort;


}