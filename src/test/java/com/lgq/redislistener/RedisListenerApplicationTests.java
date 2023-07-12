package com.lgq.redislistener;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageInfo;
import com.lgq.sharding.entity.AlarmEvent;
import com.lgq.sharding.entity.AlarmStatQuery;
import com.lgq.sharding.mapper.AlarmEventMapper;
import com.lgq.sharding.service.AlarmEventService;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

@SpringBootTest
class RedisListenerApplicationTests {

    @Autowired
    private AlarmEventService alarmEventService;
    @Autowired
    private AlarmEventMapper mapper;
    @Test
    void contextLoads() {
        AlarmStatQuery query = new AlarmStatQuery();
        int pageSize=1;
        query.setPageSize(1000);
        query.setStartTime(DateUtil.parse("2023-06-01 00:00:00"));
        query.setEndTime(DateUtil.parse("2023-06-30 24:00:00"));
        while (true){
            query.setPageNum(pageSize);
            PageInfo<AlarmEvent> page = alarmEventService.getList1(query);
            if(page.getSize()==0){
                return;
            }
            if(page.getSize()==1000){
                pageSize++;
            }
            List<AlarmEvent> list = page.getList();
            Map<String, List<AlarmEvent>> map= new HashMap<>();
            for (int i = 0; i < list.size(); i++) {
                AlarmEvent alarmEvent=list.get(i);
                Date alarmStartTime = alarmEvent.getAlarmStartTime();
                int week = DateUtil.weekOfMonth(alarmStartTime);
                String weekStr = String.valueOf(week);
                String format = StrUtil.format("{}{}", DateUtil.format(alarmStartTime, DatePattern.SIMPLE_MONTH_PATTERN), weekStr);
                if (map.containsKey(format)) {
                    List<AlarmEvent> alarmEvents = map.get(format);
                    alarmEvents.add(alarmEvent);
                }else {
                    ArrayList<AlarmEvent> alarmEvents = new ArrayList<>();
                    alarmEvents.add(alarmEvent);
                    map.put(format,alarmEvents);
                }
            }
            Iterator<String> iterator = map.keySet().iterator();
            while (iterator.hasNext()){
                String next = iterator.next();
                List<AlarmEvent> alarmEvents = map.get(next);
                String name="alarm_event_" + next;
                try {
                    alarmEventService.createTable(name);
                } catch (Exception e) {
                }
                alarmEventService.insert(alarmEvents,next);
            }
        }

    }

}
