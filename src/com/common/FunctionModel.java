package com.common;

import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.functions.AbstractFunction;
import org.apache.jmeter.functions.InvalidVariableException;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * 函数模板code-复制该class到functions包即可快速修改
 * @author Fan QingChuan
 */

public class FunctionModel extends AbstractFunction {

    //函数名称 及 函数备注描述
    private static final String KEY = "___FunctionModel";
    private static final List<String> DESC = new LinkedList<>();

    //函数（参数）描述
    static {
        DESC.add("参数说明1,多个多行");
        DESC.add("参数说明2,多个多行");
    }

    /**
     * 编码生成目标值 {@link FunctionModel}
     * @param sampleResult
     * @param sampler
     * @return
     * @throws InvalidVariableException
     */
    @Override
    public String execute(SampleResult sampleResult, Sampler sampler) throws InvalidVariableException {

        return null;
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
