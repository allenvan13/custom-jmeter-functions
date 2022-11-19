package com.functions;

import com.utils.IdWorker;
import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.functions.AbstractFunction;
import org.apache.jmeter.functions.InvalidVariableException;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * 雪花算法生成数字ID
 * @author Fan QingChuan
 * @since 2022/11/19 20:39
 */

public class SnowFlakeID extends AbstractFunction {

    //function名称
    private static final String KEY = "___SnowFlakeID";
    private static final List<String> DESC = new LinkedList<>();

    //自定义function的参数描述
    static {
        DESC.add("雪花算法生成数字ID 不输入参数");
    }

    /**
     * 编码生成目标值 {@link SnowFlakeID}
     * @param sampleResult
     * @param sampler
     * @return
     */
    @Override
    public String execute(SampleResult sampleResult, Sampler sampler){

        return String.valueOf(new IdWorker(1,1,22).nextId());
    }

    /**
     * 设置用户输入的参数
     * @param parameters
     */
    @Override
    public void setParameters(Collection<CompoundVariable> parameters){

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
