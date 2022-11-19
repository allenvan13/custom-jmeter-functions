package com.functions;

import com.common.ChineseCharUtils;
import org.apache.jmeter.engine.util.CompoundVariable;
import org.apache.jmeter.functions.AbstractFunction;
import org.apache.jmeter.functions.InvalidVariableException;
import org.apache.jmeter.samplers.SampleResult;
import org.apache.jmeter.samplers.Sampler;

import java.util.*;

/**
 * 随机生成中文姓名
 * @author Fan QingChuan
 * @since 2022/11/19 20:20
 */

public class ChineseName extends AbstractFunction {

    private static final String KEY = "___ChineseName";
    private static final List<String> DESC = new LinkedList<>();

    static {
        DESC.add("随机生成中文姓名 不输入参数");
    }

    private static final String[] FIRST_NAMES = new String[] { "李", "王", "张",
            "刘", "陈", "杨", "黄", "赵", "周", "吴", "徐", "孙", "朱", "马", "胡", "郭", "林",
            "何", "高", "梁", "郑", "罗", "宋", "谢", "唐", "韩", "曹", "许", "邓", "萧", "冯",
            "曾", "程", "蔡", "彭", "潘", "袁", "於", "董", "余", "苏", "叶", "吕", "魏", "蒋",
            "田", "杜", "丁", "沈", "姜", "范", "江", "傅", "钟", "卢", "汪", "戴", "崔", "任",
            "陆", "廖", "姚", "方", "金", "邱", "夏", "谭", "韦", "贾", "邹", "石", "熊", "孟",
            "秦", "阎", "薛", "侯", "雷", "白", "龙", "段", "郝", "孔", "邵", "史", "毛", "常",
            "万", "顾", "赖", "武", "康", "贺", "严", "尹", "钱", "施", "牛", "洪", "龚", "东方",
            "夏侯", "诸葛", "尉迟", "皇甫", "宇文", "鲜于", "西门", "司马", "独孤", "公孙", "慕容", "轩辕",
            "左丘", "欧阳", "皇甫", "上官", "闾丘", "令狐" };

    @Override
    public String execute(SampleResult sampleResult, Sampler sampler) {
        return genFirstName()
                + ChineseCharUtils.genRandomLengthChineseChars(1, 2);
    }

    @Override
    public void setParameters(Collection<CompoundVariable> parameters) throws InvalidVariableException {
        //校验参数个数
        checkParameterCount(parameters,0,1);
    }

    @Override
    public String getReferenceKey() {
        return KEY;
    }

    @Override
    public List<String> getArgumentDesc() {
        return DESC;
    }

    private String genFirstName() {
        return FIRST_NAMES[new Random(new Date().getTime()).nextInt(FIRST_NAMES.length)];
    }

}
