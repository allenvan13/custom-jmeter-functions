package com.functions;

import com.common.ChineseAreaList;
import com.common.ChineseCharUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.functions.AbstractFunction;
import org.apache.jmeter.functions.InvalidVariableException;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * 随机生成中文地址
 * @author Fan QingChuan
 * @since 2022/11/19 23:38
 */

public class ChineseAddress extends AbstractFunction {
    //function名称
    private static final String KEY = "___ChineseAddress";
    private static final List<String> DESC = new LinkedList<>();

    private String provinceAndCityName;

    //自定义function的参数描述
    static {
        DESC.add("随机生成中文地址, 不输入或1个参数: 例如 希望省、四川省、香港特别行政区");
    }

    /**
     * 编码生成目标值 {@link ChineseAddress}
     * @param sampleResult
     * @param sampler
     * @return
     * @throws InvalidVariableException
     */
    @Override
    public String execute(SampleResult sampleResult, Sampler sampler) throws InvalidVariableException {
        StringBuilder result;
        if (ObjectUtils.isNotEmpty(provinceAndCityName)) {
            result = new StringBuilder(provinceAndCityName);
        }else {
            result = new StringBuilder(genProvinceAndCity());
        }

        result.append(ChineseCharUtils.genRandomLengthChineseChars(2, 3) + "路");
        result.append(RandomUtils.nextInt(1, 8000) + "号");
        result.append(ChineseCharUtils.genRandomLengthChineseChars(2, 3) + "小区");
        result.append(RandomUtils.nextInt(1, 20) + "单元");
        result.append(RandomUtils.nextInt(101, 2500) + "室");
        return result.toString();
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
            provinceAndCityName = ((CompoundVariable) values[0]).execute();
        } else {
            provinceAndCityName = null;
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

    private static String genProvinceAndCity() {
        return ChineseAreaList.provinceCityList.get(
                RandomUtils.nextInt(0, ChineseAreaList.provinceCityList.size()));
    }
}
