package com.lgq.strategy;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

/**
 * @InterfaceName: Handle
 * @author: luogongquan
 * @since: 2023/7/31 18:30
 */
public abstract class Handle {
    abstract String getCode();
    protected void handle(){
        System.out.println("最顶");
    }



    public static void main(String[] args) {
        DateTime parse = DateUtil.parse("2023-08-11 00:00:00");
        DateTime parse1 = DateUtil.parse("2023-08-17 23:59:59");
        System.out.println(DateUtil.betweenDay(parse, parse1, true));
        System.out.println(DateUtil.beginOfDay(DateUtil.offsetDay(parse1, -6)));

    }
}
