package com.lgq.sharding.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.segments.MergeSegments;
import com.lgq.sharding.entity.Sharding;
import com.lgq.sharding.service.ShardingService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
                .ge(StringUtils.isNotBlank(startTime), Sharding::getTime, startTime)
                .le(StringUtils.isNotBlank(endTime), Sharding::getTime, endTime)
                .eq(StringUtils.isNotBlank(time),Sharding::getTime,time);
        List<Sharding> objects = service.list(between);
        return objects;
    }
}
