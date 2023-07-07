package com.lgq.sharding.config;

import com.google.common.collect.Range;
import org.apache.commons.lang3.StringUtils;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: luogongquan
 * @date: 2023/4/13 16:58
 * @Description:
 **/
public class RangeTableShardingAlgorithm implements RangeShardingAlgorithm<Integer> {

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Integer> rangeShardingValue) {
        System.out.println("=============================================="+rangeShardingValue);
        Range<Integer> valueRange = rangeShardingValue.getValueRange();
        Integer lower = valueRange.lowerEndpoint();
        Integer upper = valueRange.upperEndpoint();

        List<String> collect = collection.stream().filter(item -> {
            String substring = StringUtils.substring(item, item.indexOf("_")+1, item.length());
            Integer integer = Integer.valueOf(substring);
            return integer >= lower && integer <= upper;
        }).collect(Collectors.toList());
        return collect;
    }

    /**
     * 构建分片后的表名
     * @param logicTableName
     * @param date
     * @return
     */
    private String buildShardingTable(String logicTableName, String date) {
        String substring = StringUtils.substring(date, 0, date.lastIndexOf("-"));
        String replace = substring.replace("-", "");
        StringBuffer stringBuffer = new StringBuffer(logicTableName).append("_").append( replace);
        return stringBuffer.toString();
    }
}
