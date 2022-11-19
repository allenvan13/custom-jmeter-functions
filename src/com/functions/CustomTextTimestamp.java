package com.functions;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.functions.AbstractFunction;
import org.apache.jmeter.functions.InvalidVariableException;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 自定义文本（自动拼接时间戳）
 * @author Fan QingChuan
 * @since 2022/11/19 23:39
 */

public class CustomTextTimestamp extends AbstractFunction {

    //function名称
    private static final String KEY = "___CustomTextTimestamp";
    private static final List<String> DESC = new LinkedList<>();

    private String prefixString;

    //自定义function的参数描述
    static {
        DESC.add("自定义文本（自动拼接时间戳） -参数-文本前缀 ");
    }

    /**
     * 编码生成目标值 {@link CustomTextTimestamp}
     * @param sampleResult
     * @param sampler
     * @return
     * @throws InvalidVariableException
     */
    @Override
    public String execute(SampleResult sampleResult, Sampler sampler) throws InvalidVariableException {

        Date time = Calendar.getInstance().getTime();
        return prefixString.concat(new SimpleDateFormat("yyyy_MM_dd_HHmmss_SSS").format(time));
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
            String prefixString = ((CompoundVariable) values[0]).execute();
            this.prefixString = ObjectUtils.isNotEmpty(prefixString) ? prefixString : "中文文字: ";
        }else {
            this.prefixString = "中文文字: ";
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
