package com.functions;

import com.alibaba.fastjson.JSONObject;
import com.model.ProviderFile;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.functions.AbstractFunction;
import org.apache.jmeter.functions.InvalidVariableException;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;

import java.util.*;

/**
 * 函数模板code-复制该class到functions包即可快速修改
 * @author Fan QingChuan
 */

public class CustomBasicFileDto extends AbstractFunction {

    //函数名称 及 函数备注描述
    private static final String KEY = "___CustomBasicFileDto";
    private static final List<String> DESC = new LinkedList<>();

    private int fileType;

    //函数（参数）描述
    static {
        DESC.add("自定义FileDtos 接受类型参数 （1、2、3） 1-公司logo 2-安全生产许可证 3-营业执照");
    }

    /**
     * 编码生成目标值 {@link CustomBasicFileDto}
     * @param sampleResult
     * @param sampler
     * @return
     * @throws InvalidVariableException
     */
    @Override
    public String execute(SampleResult sampleResult, Sampler sampler) throws InvalidVariableException {

        return JSONObject.toJSONString(buildNewProviderFile(fileType));
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
            String fileType = ((CompoundVariable) values[0]).execute();
            this.fileType = ObjectUtils.isNotEmpty(fileType) ? Integer.parseInt(fileType) : 4;
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
     * 生成新的ProviderFile
     * @return
     */
    private ProviderFile buildNewProviderFile(Integer fileType) {
        ProviderFile providerFile = new ProviderFile();
        switch (fileType) {
            case 1:
                providerFile.setFileUrl("https://ossnewhope.oak.net.cn/npmsrm/cooperation/1667272289948/CompanyLogo-20221101.webp");
                providerFile.setFileSize(200.0);
                providerFile.setFileName("公司LOGO");
                providerFile.setFileType("companyLogo");
                break;
            case 2:
                providerFile.setFileUrl("https://ossnewhope.oak.net.cn/npmsrm/cooperation/1667272309717/ProductionLicence-20221101.webp");
                providerFile.setFileSize(300.0);
                providerFile.setFileName("安全生产许可证");
                providerFile.setFileType("safeProductionLicence");
                break;
            default:
                providerFile.setFileUrl("https://ossnewhope.oak.net.cn/npmsrm/cooperation/1667272335125/BusinessLicence-20221101.jpg");
                providerFile.setFileSize(400.0);
                providerFile.setFileName("营业执照");
                providerFile.setFileType("businessLicence");
                break;
        }
        return providerFile;
    }

}
