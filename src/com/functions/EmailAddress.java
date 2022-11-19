package com.functions;

import com.common.Email;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.functions.AbstractFunction;
import org.apache.jmeter.functions.InvalidVariableException;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;

import java.util.*;

/**
 * 随机生成Email地址
 * @author Fan QingChuan
 * @since 2022/11/19 20:39
 */

public class EmailAddress extends AbstractFunction {

    //function名称
    private static final String KEY = "___EmailAddress";
    private static final List<String> DESC = new LinkedList<>();

    //自定义function的参数描述
    static {
        DESC.add("随机生成Email地址 不输入参数");
    }

    /**
     * 编码生成目标值 {@link EmailAddress}
     * @param sampleResult
     * @param sampler
     * @return
     */
    @Override
    public String execute(SampleResult sampleResult, Sampler sampler) {

        StringBuilder result = new StringBuilder();
        result.append(RandomStringUtils.randomAlphanumeric(10));
        result.append(Email.SUFFIX_HOST[new Random(new Date().getTime()).nextInt(Email.SUFFIX_HOST.length)]);

        return result.toString().toLowerCase();
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
