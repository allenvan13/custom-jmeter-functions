package com.common;

/**
 * @author Fan QingChuan
 * @since 2022/12/8 17:06
 */

public class ApiBid {

    //查询用户拥有的组织树架构
    public static final String COMMON_GET_USER_TREE = "/prov-bid/common/getUserTree";

    //字典项-列表
    public static final String BIDDICT_LIST = "/prov-bid/bidDict/list";

    //提交审核计划- 保存/提交审核
    public static final String PLAN_SAVE_OR_SUBMIT = "/prov-bid/purchasePlan/saveOrSubmit";

    //发起采购
    public static final String PLAN_STARTPLANNING = "/prov-bid/purchasePlan/startPlanning?planId=";

    //采购计划- 保存
    public static final String PLAN_SAVE = "/prov-bid/purchasePlan/save";

    //采购计划- 删除
    public static final String PLAN_DELETE = "/prov-bid/purchasePlan/delete";

    //采购计划- 详情
    public static final String PLAN_DETAIL = "/prov-bid/purchasePlan/detail?planId=%s";

    //采购方式详情信息
    public static final String PURCHASE_METHOD_DETAIL = "/prov-bid/purchaseMethod/detail/%s";

    //采购方式列表
    public static final String PURCHASE_METHOD_LIST = "/prov-bid/purchaseMethod/list";

    //采购类别列表
    public static final String PURCHASE_CATEGORY_LIST = "/prov-bid/purchaseCategory/list";

    //根据采购类别id获取采购方式
    public static final String PURCHASE_CATEGORY_METHOD = "/prov-bid/purchaseCategory/getPurchaseMethods?id=%s";

    //流程回调
    public static final String PURCHASE_PROCESS_BACK = "/prov-bid/process/back";


}
