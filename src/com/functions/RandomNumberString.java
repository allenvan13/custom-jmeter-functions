package com.functions;

import cn.hutool.core.util.RandomUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.functions.AbstractFunction;
import org.apache.jmeter.functions.InvalidVariableException;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;

import java.util.*;

/**
 * 随机生成数字字符串
 * @author Fan QingChuan
 * @since 2022/11/19 20:39
 */

public class RandomNumberString extends AbstractFunction {

    //function名称
    private static final String KEY = "___RandomNumberString";
    private static final List<String> DESC = new LinkedList<>();

    private int length = 20;

    //自定义function的参数描述
    static {
        DESC.add("随机生成数字字符串 输入参数:长度 默认20");
    }

    /**
     * 编码生成目标值 {@link RandomNumberString}
     * @param sampleResult
     * @param sampler
     * @return
     */
    @Override
    public String execute(SampleResult sampleResult, Sampler sampler) {

        return RandomUtil.randomNumbers(length);
    }

    /**
     * 设置用户输入的参数
     * @param parameters
     */
    @Override
    public void setParameters(Collection<CompoundVariable> parameters) {

        Object[] values = parameters.toArray();
        if (values.length > 0) {
            String length = ((CompoundVariable) values[0]).execute();
            if (ObjectUtils.isNotEmpty(length)) {
                this.length = Integer.parseInt(length);
            }
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
}
