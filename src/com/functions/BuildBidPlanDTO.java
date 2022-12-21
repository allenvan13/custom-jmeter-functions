package com.functions;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.net.URLEncoder;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.common.*;
import com.jayway.jsonpath.JsonPath;
import com.model.*;
import com.utils.HttpUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.functions.AbstractFunction;
import org.apache.jmeter.functions.InvalidVariableException;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * 函数模板code-复制该class到functions包即可快速修改
 * @author Fan QingChuan
 */

public class BuildBidPlanDTO extends AbstractFunction {

    //函数名称 及 函数备注描述
    private static final String KEY = "___BuildBidPlanDTO";
    private static final List<String> DESC = new LinkedList<>();
    private static Map<String,String> header;
    private static final String HOST = "http://192.168.1.128:7100";
    private static final List<BidUcUser> allTestUsers = Arrays.stream(TestUserEnum.values()).map(u -> {
        BidUcUser bidUcUser = new BidUcUser();
        bidUcUser.setType(u.getType());
        bidUcUser.setBidNodeCode(u.getBidNodeCode());
        bidUcUser.setUserId(Long.parseLong(u.getUserId()));
        bidUcUser.setRealName(u.getRealname());
        bidUcUser.setUserName(u.getUsername());
        return bidUcUser;
    }).collect(Collectors.toList());
    private static final List<Project> allProject = Arrays.stream(ProjectEnum.values()).map(o -> {return Project.builder().projectId(o.getProjectId()).projectName(o.getProjectName()).build();}).collect(Collectors.toList());
    private static final List<String> allProduct = Arrays.asList("毛巾","牙刷","检测设备","N95","东鹏瓷砖","欧尚台灯");

    private String planName;
    private String projectName;
    private String purchaseCategoryName;
    private String purchaseMethodName;
    private String providerCategoryId;
    private String providerCategoryName;
    private Boolean isDeletedNodes;

    //函数（参数）描述
    static {
        header = new HashMap<>();
        StringBuilder body = new StringBuilder("username=");
        body.append("ATE002")
                .append("&password=")
                .append("14f47e684733e4811b718840a1fee894")
                .append("&saved_request_url=");

        URLEncoder encoder = URLEncoder.createDefault();
        String url = HOST.concat("/auth/oauth/authorize?client_id=upms&response_type=code&redirect_uri=".concat(encoder.encode("http://192.168.1.128:7080/#/cooperation-manage/home", StandardCharsets.UTF_8)));
        body.append(url);
        HttpResponse rs = HttpRequest.post(HOST.concat("/auth/authentication/form"))
                .setFollowRedirects(false)
                .body(body.toString())
                .executeAsync();

        rs = HttpRequest.get(url)
                .cookie(rs.getCookies())
                .setFollowRedirects(false)
                .executeAsync();

        HttpRequest.get(rs.header("Location"))
                .setFollowRedirects(false)
                .cookie(rs.getCookies())
                .executeAsync();
        String code = rs.header("Location").split("code=")[1];

        rs = HttpRequest.post(HOST.concat("/auth/oauth/token").concat("?grant_type=authorization_code&code=").concat(code))
                .header("Authorization", "Basic dXBtczp1cG1z")
                .execute();

        Map<String,String> header_temp = new HashMap<>();
        header_temp.put("Authorization","Bearer " + JsonPath.read(rs.body(),"$.body.access_token").toString());

        String rsStr = HttpRequest.post(HOST.concat("/auth/user/token/convert"))
                .addHeaders(header_temp)
                .body("{\"clientId\":\"app_7ab8c333\"}")
                .execute().body();

        header.put("Authorization","Bearer " + JsonPath.read(rsStr,"$.body.access_token").toString());

        DESC.add("采购计划名称，选填");
        DESC.add("项目名称，选填");
        DESC.add("采购类别，选填");
        DESC.add("采购方式，选填");
        DESC.add("供应商类别ID，选填");
        DESC.add("供应商类别名称，选填");
        DESC.add("是否删除非必要节点，选填");
    }

    /**
     * 编码生成目标值 {@link BuildBidPlanDTO}
     * @param sampleResult
     * @param sampler
     * @return
     * @throws InvalidVariableException
     */
    @Override
    public String execute(SampleResult sampleResult, Sampler sampler) throws InvalidVariableException {

        return JSONObject.toJSONString(buildPlanDto(planName,projectName,purchaseCategoryName,purchaseMethodName,providerCategoryId,providerCategoryName,isDeletedNodes));
    }

    /**
     * 设置用户输入的参数
     * @param parameters
     * @throws InvalidVariableException
     */
    @Override
    public void setParameters(Collection<CompoundVariable> parameters) throws InvalidVariableException {
        //校验参数个数
        checkParameterCount(parameters,0,DESC.size());
        //将值存入变量中
        Object[] values = parameters.toArray();

        if (values.length > 0) {

            String planName = ((CompoundVariable) values[0]).execute();
            this.planName = ObjectUtils.isNotEmpty(planName) ? planName : null;

            String projectName = ((CompoundVariable) values[1]).execute();
            this.projectName = ObjectUtils.isNotEmpty(projectName) ? projectName : null;

            String purchaseCategoryName = ((CompoundVariable) values[2]).execute();
            this.purchaseCategoryName = ObjectUtils.isNotEmpty(purchaseCategoryName) ? purchaseCategoryName : null;

            String purchaseMethodName = ((CompoundVariable) values[3]).execute();
            this.purchaseMethodName = ObjectUtils.isNotEmpty(purchaseMethodName) ? purchaseMethodName : null;

            String providerCategoryId = ((CompoundVariable) values[4]).execute();
            this.providerCategoryId = ObjectUtils.isNotEmpty(providerCategoryId) ? providerCategoryId : null;

            String providerCategoryName = ((CompoundVariable) values[5]).execute();
            this.providerCategoryName = ObjectUtils.isNotEmpty(providerCategoryName) ? providerCategoryName : null;

            String isDeletedNodes = ((CompoundVariable) values[6]).execute();
            this.isDeletedNodes = ObjectUtils.isNotEmpty(isDeletedNodes) ? Boolean.parseBoolean(isDeletedNodes) : null;
        }
    }

    @Override
    public String getReferenceKey() {
        return KEY;
    }

    @Override
    public List<String> getArgumentDesc() {
        return DESC;
    }


    /**
     * 创建Plan 采购计划DTO对象
     * @param planName  计划名称 选填 不填则按代码规则随机生成
     * @param projectName 项目名称 选填 项目范围在枚举类 ProjectEnum 中配置，若需扩大范围，需自行填加
     * @param purchaseCategoryName 采购类别 选填 若指定，则匹配   若匹配不到或不指定，则随机设置类别
     * @param purchaseMethodName 采购方式 选填 若指定，则匹配   若匹配不到或不指定，随机设置采购方式
     * @param providerCategoryId  供应商类别ID 选填  不填则随机
     * @param providerCategoryName  供应商类别名称 选填 不填则随机
     * @param isDeletedNodes 是否删除不必要节点
     */
    PurchasePlanDto buildPlanDto(String planName, String projectName, String purchaseCategoryName,
                                 String purchaseMethodName, String providerCategoryId,
                                 String providerCategoryName, Boolean isDeletedNodes) {

        PurchasePlanDto planDto = new PurchasePlanDto();

        //计划名称
        if (ObjectUtil.isNotEmpty(planName)) {
            //若指定名称，则按需求拼接
            planDto.setPlanName(planName);
        }else {
            //若不指定名称，则按不重复拼接
            String format = String.format("自动化测试-[%s]采购计划", allProduct.get(RandomUtil.randomInt(allProduct.size())).concat(RandomUtil.randomStringUpper(4)));
            planDto.setPlanName(format+RandomUtil.randomNumbers(5));
        }

        //设置 不具备依赖性的字段值
        planDto.setEstimatedAmount(new BigDecimal(RandomUtil.randomDouble(9999999999.99,2, RoundingMode.HALF_UP)).setScale(2,RoundingMode.HALF_UP));    //预计签约金额,元
        planDto.setRecordTime(RandomUtil.randomDay(-1000,0));                             //入场时间
        planDto.setPlanStartTime(RandomUtil.randomDay(-100,0));                           //计划开始时间
        planDto.setPlanFinishTime(RandomUtil.randomDay(1000,2000));                        //计划完成时间


        //项目分期
        Project targetProject;
        if (ObjectUtil.isNotEmpty(projectName)) {
            //-若指定 则匹配（匹配不到 则随机）
            targetProject = allProject.parallelStream().filter(o -> projectName.equals(o.getProjectName())).findAny().orElse(allProject.get(RandomUtil.randomInt(allProject.size())));
        }else {
            //若不指定 则随机
            targetProject = allProject.get(RandomUtil.randomInt(allProject.size()));
        }
        planDto.setProjectId(Long.parseLong(targetProject.getProjectId()));
        planDto.setProjectName(targetProject.getProjectName());

        //采购类别
        //调用API-采购类别列表 查询启用状态的采购类别List
        String rs = HttpUtils.doGet(HOST.concat(ApiBid.PURCHASE_CATEGORY_LIST), header, "status=true");
        List<JSONObject> categoryList = JSON.parseObject(rs).getJSONArray("body").toJavaList(JSONObject.class);

        PurchaseCategory targetCategory = null;
        //获取目标采购类别 若指定，则匹配
        if (ObjectUtil.isNotEmpty(purchaseCategoryName)) {
            targetCategory = categoryList.stream().filter(cate -> purchaseCategoryName.equals(cate.getString("name")))
                    .map(cate -> {
                        return PurchaseCategory.builder()
                                .purchaseCategoryName(cate.getString("name"))
                                .purchaseCategoryId(cate.getString("id"))
                                .build();
                    }).findAny().orElse(null);
        }

        //若匹配不到或不指定，则随机设置类别
        if (null == targetCategory) {
            JSONObject cate = categoryList.get(RandomUtil.randomInt(categoryList.size()));
            targetCategory = PurchaseCategory.builder().purchaseCategoryId(cate.getString("id")).purchaseCategoryName(cate.getString("name")).build();
        }
        planDto.setPurchaseCategoryName(targetCategory.getPurchaseCategoryName());
        planDto.setPurchaseCategoryId(Long.parseLong(targetCategory.getPurchaseCategoryId()));

        //采购方式
        //调用API-根据ID获取采购方式详情   ID来自类别targetCategory
        rs = HttpUtils.doGet(HOST.concat(String.format(ApiBid.PURCHASE_CATEGORY_METHOD, targetCategory.getPurchaseCategoryId())), header);
        List<JSONObject> methodList = JSON.parseObject(rs).getJSONArray("body").toJavaList(JSONObject.class);
        PurchaseMethod targetMethod = null;
        //获取采购类别关联的 采购方式  若指定，则匹配
        if (ObjectUtil.isNotEmpty(purchaseMethodName)) {
            targetMethod = methodList.stream().filter(method -> purchaseMethodName.equals(method.getString("name")))
                    .map(method -> {
                        return PurchaseMethod.builder()
                                .purchaseMethodCode(method.getString("code"))
                                .purchaseMethodName(method.getString("name"))
                                .ifControlPriceFloor(method.getBoolean("ifControlPriceFloor"))
                                .ifEvaluationStaffGteThree(method.getBoolean("ifEvaluationStaffGteThree"))
                                .ifEvaluationStaffOdd(method.getBoolean("ifEvaluationStaffOdd"))
                                .ifSignMoneyControl(method.getBoolean("ifSignMoneyControl"))
                                .ifStrategic(method.getBoolean("ifStrategic"))
                                .ifTn(method.getBoolean("ifTn"))
                                .build();
                    }).findAny().orElse(null);
        }
        //若匹配不到或不指定，随机设置采购方式
        if (null == targetMethod) {
            JSONObject method = methodList.get(RandomUtil.randomInt(methodList.size()));
            targetMethod = PurchaseMethod.builder()
                    .purchaseMethodCode(method.getString("code"))
                    .purchaseMethodName(method.getString("name"))
                    .ifControlPriceFloor(method.getBoolean("ifControlPriceFloor"))
                    .ifEvaluationStaffGteThree(method.getBoolean("ifEvaluationStaffGteThree"))
                    .ifEvaluationStaffOdd(method.getBoolean("ifEvaluationStaffOdd"))
                    .ifSignMoneyControl(method.getBoolean("ifSignMoneyControl"))
                    .ifStrategic(method.getBoolean("ifStrategic"))
                    .ifTn(method.getBoolean("ifTn"))
                    .build();
        }

        //浅拷贝 targetMethod所有属性到planDto
        BeanUtil.copyProperties(targetMethod,planDto);

        //议标类型
        rs = HttpUtils.doGet(HOST.concat(ApiBid.BIDDICT_LIST), header, "type=bidType&status=true");
        List<JSONObject> bidDictList = JSONObject.parseObject(rs).getJSONArray("body").toJavaList(JSONObject.class);
        //优先取默认的
        JSONObject bidType = bidDictList.stream().filter(json -> json.getBoolean("ifDefault")).findAny().orElse(null);
        if (null == bidType) {
            //若不存在默认的，则随机取
            bidType = bidDictList.get(RandomUtil.randomInt(bidDictList.size()));
        }
        planDto.setBidTypeName(bidType.getString("name"));
        planDto.setBidTypeValue(bidType.getString("value"));

        //供应商类别
        List<String> categoryIdList = StaticProviderCategory.providerCategoryIdList;
        if (ObjectUtil.isEmpty(providerCategoryId) || ObjectUtil.isEmpty(providerCategoryName)) {
            int index = RandomUtil.randomInt(categoryIdList.size());
            planDto.setProviderCategoryId(categoryIdList.get(index));
            planDto.setProviderCategoryName(StaticProviderCategory.providerCategoryNameList.get(index));
        }else {
            planDto.setProviderCategoryId(providerCategoryId);
            planDto.setProviderCategoryName(providerCategoryName);
        }

        //设置 招标经办人
        TestUserEnum chargeUser = TestUserEnum.ATEzbjbr;
        planDto.setManagerUserId(Long.parseLong(chargeUser.getUserId()));
        planDto.setManagerUserName(chargeUser.getRealname());
        planDto.setManagerUserCode(chargeUser.getUsername());

        //采购节点List
        planDto.setNodes(buildPlanNodeDtoList(planDto.getPurchaseMethodCode(),isDeletedNodes));
        return planDto;
    }

    /**
     * 创建采购计划（过程）节点DTO List<PlanNodeDto>
     * @param purchaseMethod 采购方法编码
     * @param isDeletedNodes 是否删除非必要节点
     * @return
     */
    List<PlanNodeDto> buildPlanNodeDtoList(String purchaseMethod, Boolean isDeletedNodes) {

        //调用接口（API功能-根据采购方式编码获取详情信息-含关联的采购节点信息)
        String apiUrl = String.format(HOST.concat(ApiBid.PURCHASE_METHOD_DETAIL), purchaseMethod);
        String rs = HttpUtils.doGet(apiUrl, header);

        //解析响应json拿到所有的节点List
        List<JSONObject> purchaseMethodNodes = JSON.parseObject(rs).getJSONObject("body").getJSONArray("purchaseMethodNodes").toJavaList(JSONObject.class);
        if (isDeletedNodes) {
            purchaseMethodNodes = purchaseMethodNodes.stream().filter(node -> node.getBoolean("ifNecessity")).collect(Collectors.toList());
        }

        DateTime now = DateUtil.date();
        AtomicInteger loopInt = new AtomicInteger(1);

        List<PlanNodeDto> nodeDtoList = new ArrayList<>(purchaseMethodNodes.size());

        purchaseMethodNodes.forEach(node -> {

            PlanNodeDto nodeDto = new PlanNodeDto();

            nodeDto.setName(node.getString("name"));                                //节点名称
            nodeDto.setPurchaseMethodCode(node.getString("purchaseMethodCode"));    //关联采购方式编码
            nodeDto.setPurchaseMethodNodeCode(node.getString("code"));              //关联采购方式节点编码
            //责任部门
            nodeDto.setChargeOrg(node.getString("responsibleDept"));
            nodeDto.setChargeOrgValue(node.getString("responsibleDeptName"));
            nodeDto.setIfNecessity(node.getBoolean("ifNecessity"));                         //是否必要节点
            nodeDto.setSort(node.getInteger("sort"));                                       //排序
            nodeDto.setStartTime(DateUtil.offsetDay(now,loopInt.getAndAdd(10)));           //节点计划开始时间
            nodeDto.setFinishTime(DateUtil.offsetDay(now,loopInt.getAndAdd(10)));          //节点计划完成时间

            //节点经办人
            BidUcUser chargeUser = allTestUsers.stream().filter(u -> node.getString("code").equals(u.getBidNodeCode()) && "1".equals(u.getType())).findFirst().orElse(null);

            nodeDto.setChargeUserId(chargeUser.getUserId());
            nodeDto.setChargeUserName(chargeUser.getRealName());
            nodeDto.setChargeUserCode(chargeUser.getUserName());

            nodeDtoList.add(nodeDto);

        });

        return nodeDtoList;


    }

}
