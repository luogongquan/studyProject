package com.lgq.sharding.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lgq.sharding.entity.*;
import com.lgq.sharding.mapper.AlarmEventMapper;
import org.apache.shardingsphere.api.hint.HintManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: AlarmEventService
 * @author: luogongquan
 * @since: 2023/7/10 10:40
 */
@Service
public class AlarmEventService  extends ServiceImpl<AlarmEventMapper, AlarmEvent> {
    public List<AlarmEventCount4Hour> getAlarmEventCount4Hour(AlarmStatQuery query) {
        HintManager.clear();
        HintManager hintManager = HintManager.getInstance();
        //  hintManager.setDatabaseShardingValue(0);
        ShardingPojo sharding = new ShardingPojo();
        sharding.setStartTime("202306");
        hintManager.addTableShardingValue("alarm_event", sharding);
        return baseMapper.getAlarmEventCount4Hour(query);
    }
    public List<AlarmCountTop> alarmCount4RiskType(AlarmStatQuery query) {
        HintManager.clear();
        HintManager hintManager = HintManager.getInstance();
        //  hintManager.setDatabaseShardingValue(0);
        ShardingPojo sharding = new ShardingPojo();
        sharding.setStartTime("202306");
        sharding.setEndTime("202306");
        hintManager.addTableShardingValue("alarm_event", sharding);
        return baseMapper.alarmCount4RiskType(query);
    }

    public List<RiskAndHandleCount> getRiskAndHandleCount(AlarmStatQuery query) {
        HintManager.clear();
        HintManager hintManager = HintManager.getInstance();
        //  hintManager.setDatabaseShardingValue(0);
        ShardingPojo sharding = new ShardingPojo();
        sharding.setStartTime("202306");
        hintManager.addTableShardingValue("alarm_event", sharding);
        return baseMapper.getRiskAndHandleCount(query);
    }
}
