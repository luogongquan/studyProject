package com.lgq.sharding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lgq.sharding.entity.*;

import java.util.List;

/**
 * @Author: luogongquan
 * @date: 2023/4/13 17:09
 * @Description:
 **/
public interface AlarmEventMapper extends BaseMapper<AlarmEvent> {
    List<AlarmEventCount4Hour> getAlarmEventCount4Hour(AlarmStatQuery query);

    List<AlarmCountTop> alarmCount4RiskType(AlarmStatQuery query);
    List<RiskAndHandleCount> getRiskAndHandleCount(AlarmStatQuery query);
}
