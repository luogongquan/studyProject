package com.lgq.sharding.controller;

import com.github.pagehelper.PageInfo;
import com.lgq.sharding.entity.*;
import com.lgq.sharding.service.AlarmEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: AlarmEventController
 * @author: luogongquan
 * @since: 2023/7/10 10:42
 */
@RequestMapping("/alarm")
@RestController
public class AlarmEventController {
    @Autowired
    private AlarmEventService alarmEventService;


    @PostMapping("/getAlarmEventCount4Hour")
    public List<AlarmEventCount4Hour> getAlarmEventCount4Hour(@RequestBody AlarmStatQuery query) {
        return alarmEventService.getAlarmEventCount4Hour(query);
    }

    @PostMapping("/alarmCount4RiskType")
    public List<AlarmCountTop> alarmCount4RiskType(@RequestBody AlarmStatQuery query) {
        return alarmEventService.alarmCount4RiskType(query);
    }
    @PostMapping("/getRiskAndHandleCount")
    public List<RiskAndHandleCount> getRiskAndHandleCount(@RequestBody AlarmStatQuery query) {
        return alarmEventService.getRiskAndHandleCount(query);
    }

    @PostMapping("/getList")
    public PageInfo<AlarmEvent> getList(@RequestBody AlarmStatQuery query) {
        return alarmEventService.getList(query,"1");
    }

    @PostMapping("/groupHaving")
    public List<CountDto> groupHaving(@RequestBody AlarmStatQuery query) {
        return alarmEventService.groupHaving(query);
    }
}
