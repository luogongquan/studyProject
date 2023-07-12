package com.lgq.sharding.strategy;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * @Author: luogongquan
 * @date: 2023/4/13 16:58
 * @Description:
 **/
public class RangeTableShardingAlgorithm1 implements RangeShardingAlgorithm<Date> {

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Date> rangeShardingValue) {
        System.out.println("=============================================="+rangeShardingValue);
        Range<Date> valueRange = rangeShardingValue.getValueRange();
        Date date = valueRange.lowerEndpoint();
        Date date1 = valueRange.upperEndpoint();
        Integer start = Integer.valueOf(DateUtil.format(date, DatePattern.SIMPLE_MONTH_PATTERN));
        Integer end = Integer.valueOf(DateUtil.format(date1, DatePattern.SIMPLE_MONTH_PATTERN));
        return collection.stream().filter(item->{
            String substring = item.substring(item.indexOf("_") + 1, item.length());
            Integer integer = Integer.valueOf(substring);
            return integer>=start && integer <=end;
        }).collect(Collectors.toList());
    }

}
