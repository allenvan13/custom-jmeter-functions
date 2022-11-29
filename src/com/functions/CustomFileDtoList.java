package com.functions;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import com.model.ProviderFile;
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

public class CustomFileDtoList extends AbstractFunction {

    //函数名称 及 函数备注描述
    private static final String KEY = "___CustomFileDtoList";
    private static final List<String> DESC = new LinkedList<>();

    //函数（参数）描述
    static {
        DESC.add("自定义CoustmFileDtoList 不接受参数");
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

        return JSONObject.toJSONString(buildListProviderFile());
    }

    /**
     * 设置用户输入的参数
     * @param parameters
     * @throws InvalidVariableException
     */
    @Override
    public void setParameters(Collection<CompoundVariable> parameters) throws InvalidVariableException {
    }

    @Override
    public String getReferenceKey() {
        return KEY;
    }

    @Override
    public List<String> getArgumentDesc() {
        return DESC;
    }

    private List<ProviderFile> buildListProviderFile() {
        return Arrays.asList(getRandomFile(RandomUtil.randomInt(1,4)));
    }
    private ProviderFile getRandomFile(Integer type) {
        ProviderFile providerFile1 = new ProviderFile();
        providerFile1.setFileSize(3000.00);
        switch (type) {
            case 1:
                providerFile1.setFileUrl("https://ossnewhope.oak.net.cn/npmsrm/cooperation/1667272535390/%E9%99%84%E4%BB%B6xlsx-20221101.xlsx");
                providerFile1.setFileName("xlsx表格");
                break;
            case 2:
                providerFile1.setFileUrl("https://ossnewhope.oak.net.cn/npmsrm/cooperation/1667272802244/%E6%B5%8B%E8%AF%95-%E6%A8%A1%E6%8B%9F%E5%90%88%E5%90%8C%E6%96%87%E6%9C%AC-20221101.docx");
                providerFile1.setFileName("docx合同");
                break;
            default:
                providerFile1.setFileUrl("https://ossnewhope.oak.net.cn/npmsrm/cooperation/1667273340709/zip%E6%96%87%E4%BB%B6-20221101.zip");
                providerFile1.setFileName("zip压缩文件");
                break;
        }
        return providerFile1;
    }


}
