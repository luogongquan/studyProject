package com.lgq.sharding.strategy;

import cn.hutool.core.util.ObjectUtil;
import com.lgq.sharding.config.TableNamesConfig;
import com.lgq.sharding.entity.ShardingPojo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shardingsphere.api.sharding.hint.HintShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.hint.HintShardingValue;

import java.util.Collection;
import java.util.Collections;
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
        if(ObjectUtil.isEmpty(pojo.getEndTime())&&ObjectUtil.isEmpty(pojo.getStartTime())&&ObjectUtil.isEmpty(pojo.getTime())){
            return CollectionUtils.emptyCollection();
        }
        if(StringUtils.isNotBlank(pojo.getTime())){

            String s = TableNamesConfig.getAllTable(logicTableName).stream().filter(name->name.substring(name.lastIndexOf("_") + 1).equals(pojo.getTime())).findAny().orElse(null);
            return Collections.singleton(s);
        }
        return TableNamesConfig.getAllTable(logicTableName).stream().filter(name -> {
        //List<String> collect = collection.stream().filter(name -> {

            String substring = name.substring(name.lastIndexOf("_") + 1);
            boolean flag1 = ObjectUtil.isEmpty(pojo.getStartTime()) || Integer.parseInt(substring) >= Integer.parseInt(pojo.getStartTime());
            boolean flag2 = ObjectUtil.isEmpty(pojo.getEndTime()) || Integer.parseInt(substring) <= Integer.parseInt(pojo.getEndTime());
            return flag1 && flag2;
        }).collect(Collectors.toList());
    }
}
