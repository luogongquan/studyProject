package com.lgq.sharding.controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lgq.sharding.entity.Sharding;
import com.lgq.sharding.service.ShardingService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.util.List;

/**
 * @Author: luogongquan
 * @date: 2023/4/13 17:08
 * @Description:
 **/
@RequestMapping("/sharding")
@RestController
public class ShardingController {
    @Autowired
    private ShardingService service;

    @RequestMapping("/getList")
    public List<Sharding> getLIst(String startTime,String endTime,String time){
        LambdaQueryWrapper<Sharding> between = new QueryWrapper<Sharding>().lambda()
                .ge(StringUtils.isNotBlank(startTime), Sharding::getDateTime, startTime)
                .le(StringUtils.isNotBlank(endTime), Sharding::getDateTime, endTime)
                .eq(StringUtils.isNotBlank(time),Sharding::getDateTime,time);
        List<Sharding> objects = service.list(between);
        return objects;
    }

    @RequestMapping("/getList1")
    public List<Sharding> getLIst1(String startTime,String endTime,String time){
        List<Sharding> objects = service.getList(time);
        return objects;
    }
    @RequestMapping("/get4String")
    public List<Sharding> get4String(){
        List<Sharding> objects = service.get4String();
        return objects;
    }

    @RequestMapping("/get4Date")
    public List<Sharding> get4Date(){
        DateTime parse = DateUtil.parse("2023-01-01");
        DateTime parse1 = DateUtil.parse("2023-02-23");
        LambdaQueryWrapper<Sharding> between = new QueryWrapper<Sharding>().lambda()
                .between(Sharding::getDateTime,parse,parse1);
        List<Sharding> objects = service.list(between);
        return objects;
    }
    @RequestMapping("/get4Date1")
    public List<Sharding> get4Date1(){
        List<Sharding> objects = service.get4Date();
        return objects;
    }
}
