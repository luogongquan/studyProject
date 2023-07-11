package com.lgq.aop;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.lgq.annotation.TableShare;
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
import java.util.Optional;

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
    @Pointcut("@within(com.lgq.annotation.TableShare) || @annotation(com.lgq.annotation.TableShare)")
    public void pointCut(){}

    @Around(value = "pointCut()")
    public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
        HintManager.clear();
        HintManager hintManager = HintManager.getInstance();
        //  hintManager.setDatabaseShardingValue(0);
        ShardingPojo sharding = new ShardingPojo();
        MethodSignature proceed = (MethodSignature)joinPoint.getSignature();
        TableShare annotation = Optional.ofNullable(proceed.getMethod().getDeclaringClass().getAnnotation(TableShare.class))
                .orElse(proceed.getMethod().getAnnotation(TableShare.class));
        String startTimeParam= annotation.startTimeParam();
        String endTimeParam= annotation.endTimeParam();
        String logicName=annotation.logicName();
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            Map<String, Object> arg = BeanUtil.beanToMap(args[i]);
            if(ObjectUtil.isEmpty(arg)){
                continue;
            }
            if(arg.containsKey(startTimeParam) && ObjectUtil.isNotEmpty(arg.get(startTimeParam))){
                sharding.setStartTime(DateUtil.format((Date) arg.get(startTimeParam), DatePattern.SIMPLE_MONTH_PATTERN));
            }
            if(arg.containsKey(endTimeParam) && ObjectUtil.isNotEmpty(arg.get(endTimeParam))){
                sharding.setEndTime(DateUtil.format((Date) arg.get(endTimeParam), DatePattern.SIMPLE_MONTH_PATTERN));
            }
        }
        hintManager.addTableShardingValue(logicName, sharding);
        Object result = joinPoint.proceed();
        HintManager.clear();
        return result;
    }


}
