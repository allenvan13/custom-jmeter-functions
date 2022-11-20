package com.functions;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.functions.AbstractFunction;
import org.apache.jmeter.functions.InvalidVariableException;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * 随机生成手机号码
 * @author Fan QingChuan
 * @since 2022/11/19 21:06
 */

public class ChineseMobileNumber extends AbstractFunction {

    //function名称
    private static final String KEY = "___ChineseMobileNumber";
    private static final List<String> DESC = new LinkedList<>();

    private String mobilePre;

    //自定义function的描述
    static {
        DESC.add("随机生成手机号码, 不输入或1个参数:  手机号前缀(例如 133、188)");
    }

    private static final int[] MOBILE_PREFIX = new int[] { 133, 153, 177, 180,
            181, 189, 134, 135, 136, 137, 138, 139, 150, 151, 152, 157, 158, 159,
            178, 182, 183, 184, 187, 188, 130, 131, 132, 155, 156, 176, 185, 186,
            145, 147, 170 };

    private static String genMobilePre() {
        return "" + MOBILE_PREFIX[RandomUtils.nextInt(0, MOBILE_PREFIX.length)];
    }

    @Override
    public String execute(SampleResult sampleResult, Sampler sampler) {
        if (ObjectUtils.isNotEmpty(mobilePre)) {
            return mobilePre + StringUtils
                    .leftPad("" + RandomUtils.nextInt(0, 99999999 + 1), 8, "0");
        }else {
            return genMobilePre() + StringUtils
                    .leftPad("" + RandomUtils.nextInt(0, 99999999 + 1), 8, "0");
        }
    }

    @Override
    public void setParameters(Collection<CompoundVariable> parameters) throws InvalidVariableException {
        //校验参数个数
        checkParameterCount(parameters,0,DESC.size());
        //将值存入变量中
        Object[] values = parameters.toArray();
        if (values.length > 0) {
            mobilePre = ((CompoundVariable) values[0]).execute();
        } else {
            mobilePre = null;
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
