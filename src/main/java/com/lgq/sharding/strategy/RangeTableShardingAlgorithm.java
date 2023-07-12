package com.lgq.sharding.strategy;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Range;
import com.lgq.sharding.config.TableNamesConfig;
import org.apache.commons.lang3.StringUtils;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: luogongquan
 * @date: 2023/4/13 16:58
 * @Description:
 **/
public class RangeTableShardingAlgorithm implements RangeShardingAlgorithm<Date> {

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Date> rangeShardingValue) {
        System.out.println("=============================================="+rangeShardingValue);
        Range<Date> valueRange = rangeShardingValue.getValueRange();
        Date lower = valueRange.lowerEndpoint();
        Date upper = valueRange.upperEndpoint();
        int lowsInt = Integer.parseInt(getString(lower));
        int upperInt = Integer.parseInt(getString(upper));
        return TableNamesConfig.getAllTable(rangeShardingValue.getLogicTableName())
                .stream()
                .filter(item -> {
                    String substring = StringUtils.substring(item, item.lastIndexOf("_") + 1, item.length());
                    int integer = Integer.parseInt(substring);
                    return integer >= lowsInt && integer <= upperInt;
                }).collect(Collectors.toList());
    }
    private static String getString(Date o) {
        int week = DateUtil.weekOfMonth(o);
        String weekStr=String.valueOf(week);
        return StrUtil.format("{}{}", DateUtil.format(o, DatePattern.SIMPLE_MONTH_PATTERN), weekStr);
    }
}
