package com.lgq.sharding.config;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: TableNamesConfig
 * @author: luogongquan
 * @since: 2023/7/11 15:07
 */

public class TableNamesConfig {
    public String[] names;
    // 起始年 默认 202306
    static String start="2023-06-01 00:00:00";

    public static   List<String> getAllTable(String logicName){
        List<String> weeksOfYear = new ArrayList<>();
        Date current=DateUtil.parse(start,DatePattern.NORM_DATETIME_PATTERN);
        Date date = new Date();
        boolean flag=current.before(date);
        while (flag) {
            int weekOfMonth = DateUtil.weekOfMonth(current);
            String yearMonth = DateUtil.format(current, DatePattern.SIMPLE_MONTH_PATTERN);
            String weekStr=String.valueOf(weekOfMonth);
            String format = StrUtil.format("{}_{}{}",logicName, yearMonth, weekStr);
            if (!weeksOfYear.contains(format)) {
                weeksOfYear.add(format);
            }
            if(current==date){
                break;
            }
            current = DateUtil.offsetWeek(current,1);
            flag=current.before(date);
            if(!flag){
                current=date;
            }
        }
        return weeksOfYear;
    }
}
