package com.lgq.sharding.strategy;

import cn.hutool.core.util.StrUtil;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

/**
 * @Author: luogongquan
 * @date: 2023/4/13 16:58
 * @Description:
 **/
public class MyPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Integer>{
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Integer> preciseShardingValue) {

        return StrUtil.format("{}_{}",preciseShardingValue.getLogicTableName(),preciseShardingValue.getValue());
    }


}
