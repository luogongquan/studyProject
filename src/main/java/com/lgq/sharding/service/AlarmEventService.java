package com.lgq.sharding.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lgq.annotation.TableShare;
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

    @TableShare
    public PageInfo<AlarmEvent>  getList(AlarmStatQuery query,String age) {
        /*HintManager.clear();
        HintManager hintManager = HintManager.getInstance();
        //  hintManager.setDatabaseShardingValue(0);
        ShardingPojo sharding = new ShardingPojo();
        sharding.setStartTime("202306");
        hintManager.addTableShardingValue("alarm_event", sharding);*/
        LambdaQueryWrapper<AlarmEvent> ge = new QueryWrapper<AlarmEvent>().lambda()
                .ge(AlarmEvent::getAlarmStartTime, query.getStartTime())
                .orderByDesc(AlarmEvent::getAlarmStartTime);
        PageHelper.startPage(query.getPageNum(),query.getPageSize());
        List<AlarmEvent> list = list(ge);
        PageInfo<AlarmEvent> page = new PageInfo<>(list);
        return page;
    }

    public PageInfo<AlarmEvent>  getList1(AlarmStatQuery query) {
        PageHelper.startPage(query.getPageNum(),query.getPageSize());
        List<AlarmEvent> list = baseMapper.findAlarmEventList(query);
        PageInfo<AlarmEvent> page = new PageInfo<>(list);
        return page;
    }

    public List<CountDto> groupHaving(AlarmStatQuery query) {
        return baseMapper.groupHaving(query);
    }
}
