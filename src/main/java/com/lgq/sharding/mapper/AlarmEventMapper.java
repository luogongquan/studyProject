package com.lgq.sharding.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lgq.sharding.entity.*;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Author: luogongquan
 * @date: 2023/4/13 17:09
 * @Description:
 **/
//@TableShare(logicName = "alarm_event")
public interface AlarmEventMapper extends BaseMapper<AlarmEvent> {
    List<AlarmEventCount4Hour> getAlarmEventCount4Hour(AlarmStatQuery query);

    List<AlarmCountTop> alarmCount4RiskType(AlarmStatQuery query);
    List<RiskAndHandleCount> getRiskAndHandleCount(AlarmStatQuery query);

    List<AlarmEvent> findAlarmEventList(AlarmStatQuery query);
    List<CountDto> groupHaving(AlarmStatQuery query);


    List<String> checkTableExistsWithShow(@Param("tableName")String tableName);

    void createTable(@Param("name")String name);


}
