package com.lgq.sharding.config;

import com.google.common.collect.Lists;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.util.Collection;
import java.util.List;

/**
 * @ClassName: TableComplexAlgorithm
 * @author: luogongquan
 * @since: 2023/7/7 16:33
 */
public class TableComplexAlgorithm implements ComplexKeysShardingAlgorithm {

    @Override
    public Collection<String> doSharding(Collection collection, ComplexKeysShardingValue complexKeysShardingValue) {
        //获取age的值
        complexKeysShardingValue.getColumnNameAndShardingValuesMap().get("age");
        List<String> dbs = Lists.newArrayList();
        return collection;
    }
}