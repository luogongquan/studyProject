package com.lgq.sharding.config;

import cn.hutool.core.util.ObjectUtil;
import com.lgq.sharding.entity.ShardingPojo;
import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @ClassName: TableHintAlgorithm
 * @author: luogongquan
 * @since: 2023/7/10 9:19
 */
public class TableHintAlgorithm implements HintShardingAlgorithm<ShardingPojo> {
    @Override
    public Collection<String> doSharding(Collection<String> collection, HintShardingValue<ShardingPojo> hintShardingValue) {
        String logicTableName = hintShardingValue.getLogicTableName();
        Collection<ShardingPojo> values = hintShardingValue.getValues();
        ShardingPojo pojo = (ShardingPojo)values.toArray()[0];
        List<String> collect = collection.stream().filter(name -> {

            String substring = name.substring(name.lastIndexOf("_") + 1, name.length());
            boolean flag1 = ObjectUtil.isEmpty(pojo.getStartTime()) || Integer.valueOf(substring) >= Integer.valueOf(pojo.getStartTime());
            boolean flag2 = ObjectUtil.isEmpty(pojo.getEndTime()) || Integer.valueOf(substring) <= Integer.valueOf(pojo.getEndTime());
            return flag1 && flag2;
        }).collect(Collectors.toList());
        return collect;
    }
}
