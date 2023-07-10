package com.lgq.aop;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONConverter;
import cn.hutool.json.JSONObject;
import com.lgq.sharding.entity.AlarmStatQuery;
import com.lgq.sharding.entity.ShardingPojo;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.hint.HintManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * 分表跨表查询处理切面
 * @Author: luogongquan
 * @date: 2023/4/21 14:14
 * @Description:
 **/

@Slf4j
@Component
@Aspect
public class TableShareAspect {
    @Pointcut("@annotation(com.lgq.annotation.TableShare)")
    public void pointCut(){}

    @Around(value = "pointCut()")
    public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
        HintManager.clear();
        HintManager hintManager = HintManager.getInstance();
        //  hintManager.setDatabaseShardingValue(0);
        ShardingPojo sharding = new ShardingPojo();

        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            Map<String, Object> arg = BeanUtil.beanToMap(args[i]);
            if(arg.containsKey("startTime") && ObjectUtil.isNotEmpty(arg.get("startTime"))){
                sharding.setStartTime(DateUtil.format((Date) arg.get("startTime"), DatePattern.SIMPLE_MONTH_PATTERN));
            }
            if(arg.containsKey("endTime") && ObjectUtil.isNotEmpty(arg.get("endTime"))){
                sharding.setEndTime(DateUtil.format((Date) arg.get("endTime"), DatePattern.SIMPLE_MONTH_PATTERN));
            }
        }
        hintManager.addTableShardingValue("alarm_event", sharding);
        Object proceed = joinPoint.proceed();
        HintManager.clear();
        return proceed;
    }


}
