package com.lgq.sharding.config;

import com.google.common.collect.Lists;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName: TableComplexAlgorithm
 * @author: luogongquan
 * @since: 2023/7/7 16:33
 */
public class TableComplexAlgorithm implements ComplexKeysShardingAlgorithm {

    @Override
    public Collection<String> doSharding(Collection collection, ComplexKeysShardingValue complexKeysShardingValue) {
        //获取age的值
        List code = (List) complexKeysShardingValue.getColumnNameAndShardingValuesMap().get("code");
        List collect = (List) collection.stream().filter(item -> {
            String s = String.valueOf(item);

            return code.contains(s.substring(s.indexOf("_") + 1, s.length()));
        }).collect(Collectors.toList());
        return collect;
    }
}