package com.functions;

import com.common.ChineseCharUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.functions.AbstractFunction;
import org.apache.jmeter.functions.InvalidVariableException;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * 自定义文本及长度（随机补全）
 * @author Fan QingChuan
 * @since 2022/11/19 23:39
 */

public class CustomLengthText extends AbstractFunction {

    //function名称
    private static final String KEY = "___CustomLengthText";
    private static final List<String> DESC = new LinkedList<>();

    private String prefixString;
    private int length;

    //自定义function的参数描述
    static {
        DESC.add("自定义文本及长度（随机补全） -参数1-文本前缀 ");
        DESC.add("自定义文本及长度（随机补全） -参数2-文本后缀长度 ");
    }

    /**
     * 编码生成目标值 {@link CustomLengthText}
     * @param sampleResult
     * @param sampler
     * @return
     * @throws InvalidVariableException
     */
    @Override
    public String execute(SampleResult sampleResult, Sampler sampler) throws InvalidVariableException {

        StringBuilder sb = new StringBuilder(prefixString);
        return sb.append(ChineseCharUtils.genFixedLengthChineseChars(length)).toString();
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

            String length = ((CompoundVariable) values[1]).execute();
            this.length = ObjectUtils.isNotEmpty(length) ? Integer.parseInt(length) : 10;
        }else {
            this.prefixString = "中文文字: ";
            this.length = 10;
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
