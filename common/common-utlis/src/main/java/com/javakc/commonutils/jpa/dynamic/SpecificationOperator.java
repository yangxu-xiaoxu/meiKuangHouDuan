package com.javakc.commonutils.jpa.dynamic;

import lombok.Data;

/**
 * 操作符类，这个类中存储了键值对和操作符号，另外存储了连接下一个条件的类型是and还是or
 * 创建时通过 id>=7,其中id就是key,>=就是oper操作符，7就是value
 * 特殊的自定义几个操作符(:表示like %v%，l:表示v%,:l表示%v) (ge表示>=，le表示<=，gt表示>，lt表示<)
 * @author leesiyu
 * @version V1.0
 * @Copyright © 北京汇才同飞教育科技公司
 */
@Data
public class SpecificationOperator {

    /**
     * 操作符的key，如查询时的name,id之类
     */
    private String key;
    /**
     * 操作符的value，具体要查询的值
     */
    private Object value;
    /**
     * 操作符，自己定义的一组操作符，用来方便查询
     */
    private String oper;
    /**
     * 连接的方式：and或者or
     */
    private String join;

    public SpecificationOperator(String key, Object value, String oper, String join) {
        this.key = key;
        this.value = value;
        this.oper = oper;
        this.join = join;
    }
    public SpecificationOperator() {
    }

}
