package com.functions;

import cn.hutool.core.util.RandomUtil;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.functions.AbstractFunction;
import org.apache.jmeter.functions.InvalidVariableException;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;

import java.math.RoundingMode;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 随机数量(金额)
 * @author Fan QingChuan
 * @since 2022/11/19 20:39
 */

public class RandomAmount extends AbstractFunction {

    //函数名称 及 函数备注描述
    private static final String KEY = "___RandomAmount";
    private static final List<String> DESC = new LinkedList<>();

    //默认最大值 及 小数位数
    private double limit = 99999999.9999;
    private int scale = 4;

    //函数（参数）描述
    static {
        DESC.add("随机数量(金额) -参数1-最大值不包括这个数 ");
        DESC.add("随机数量(金额) -参数2-保留小数位数 ");
    }

    /**
     * 编码生成目标值 {@link RandomAmount}
     * @param sampleResult
     * @param sampler
     * @return
     * @throws InvalidVariableException
     */
    @Override
    public String execute(SampleResult sampleResult, Sampler sampler) throws InvalidVariableException {

        return String.valueOf(RandomUtil.randomDouble(limit,scale, RoundingMode.HALF_UP));
    }

    /**
     * 设置解析用户输入的参数
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
            String limit = ((CompoundVariable) values[0]).execute();
            this.limit = ObjectUtils.isNotEmpty(limit) ? Double.parseDouble(limit) : 99999999.9999;

            String scale = ((CompoundVariable) values[1]).execute();
            this.scale = ObjectUtils.isNotEmpty(scale) ? Integer.parseInt(scale) : 4;
        }

    }

    /**
     * 获取KEY-Jmeter函数助手展示名称为该KEY
     * @return
     */
    @Override
    public String getReferenceKey() {
        return KEY;
    }

    @Override
    public List<String> getArgumentDesc() {
        return DESC;
    }
}
