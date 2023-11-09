package com.lgq.strategy;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @ClassName: StrategyFactory
 * @author: luogongquan
 * @since: 2023/7/31 18:38
 */
@NoArgsConstructor
@Component
public class StrategyFactory {
    Map<String, Handle> map=new ConcurrentHashMap<String,Handle>();
    @Autowired
    public StrategyFactory(List<Handle> handles) {
        handles.forEach(handle->{
            map.put(handle.getCode(),handle);
        });
    }

    public void handle(String code){
        map.get(code).handle();
    }


    public static void main(String[] args) {
        String json="{\"adasStatus\":48,\"dmsStatus\":48,\"fourthGeneration\":19,\"gpsStatus\":48,\"sdInfoList\":[{\"readWriteStatus\":49,\"remainingCapacity\":2323448064,\"storageStatus\":49,\"totalCapacity\":2705326081},{\"readWriteStatus\":49,\"remainingCapacity\":2323448065,\"storageStatus\":49,\"totalCapacity\":2705326337},{\"readWriteStatus\":49,\"remainingCapacity\":2323448065,\"storageStatus\":49,\"totalCapacity\":2722103558}],\"temperature\":1080,\"ts\":1690962563777,\"version\":\"2.2.0\",\"vin\":\"test\"}";
        DeviceReportInfo deviceReportInfo = JSONObject.parseObject(json, DeviceReportInfo.class);
        System.out.println(deviceReportInfo);
        AtomicInteger atomicInteger = new AtomicInteger(1);
        List<Integer> collect = deviceReportInfo.getSdInfoList().stream().map(item -> {
            if(atomicInteger.incrementAndGet()==2){
                return null;
            }else {
                return 1;
            }
        }).filter(ObjectUtil::isNotNull).collect(Collectors.toList());
        System.out.println(collect);
    }
}
