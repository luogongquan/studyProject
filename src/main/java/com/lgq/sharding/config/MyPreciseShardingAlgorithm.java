package com.lgq.sharding.config;

import com.google.common.collect.Range;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.RangeShardingValue;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

/**
 * @Author: luogongquan
 * @date: 2023/4/13 16:58
 * @Description:
 **/
public class MyPreciseShardingAlgorithm implements PreciseShardingAlgorithm<String>, RangeShardingAlgorithm<String> {
    /**
     * 时间格式
     */
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<String> preciseShardingValue) {
        return buildShardingTable(preciseShardingValue.getLogicTableName(), preciseShardingValue.getValue());
    }

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<String> rangeShardingValue) {
        System.out.println("=============================================="+rangeShardingValue);
        Range<String> valueRange = rangeShardingValue.getValueRange();
        String lower = valueRange.lowerEndpoint();
        String upper = valueRange.upperEndpoint();

        LocalDate start = LocalDate.parse(lower, DATE_TIME_FORMATTER);
        LocalDate end = LocalDate.parse(upper, DATE_TIME_FORMATTER);

        Collection<String> tables = new ArrayList<>();
        while (start.compareTo(end) <= 0) {
            tables.add(buildShardingTable(rangeShardingValue.getLogicTableName(), start.format(DATE_TIME_FORMATTER)));
            start = start.plusMonths(1L);
        }

        // collection配置的数据节点表，这里是排除不存在配置中的表
        collection.retainAll(tables);
        return collection;
    }

    /**
     * 构建分片后的表名
     * @param logicTableName
     * @param date
     * @return
     */
    private String buildShardingTable(String logicTableName, String date) {
        StringBuffer stringBuffer = new StringBuffer(logicTableName).append("_").append( LocalDate.parse(date, DATE_TIME_FORMATTER).format(DATE_TIME_FORMATTER), 0, 6);
        return stringBuffer.toString();
    }
}
