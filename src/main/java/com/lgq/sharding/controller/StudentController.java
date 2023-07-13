package com.lgq.sharding.controller;

import com.lgq.sharding.entity.Student;
import com.lgq.sharding.service.StudentService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author: luogongquan
 * @date: 2023/4/13 18:13
 * @Description:
 **/
@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    private StudentService service;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @RequestMapping("getList")
    public List<Student> getList(String time){
        return service.getList(time);
    }
    @RequestMapping("getCount")
    public List<Map> getCount(){
        return service.getCount();
    }

    @RequestMapping("/redis")
    public String redis(){
        return String.valueOf(stringRedisTemplate.expire("testExpireKey",30, TimeUnit.SECONDS));
    }

    @GetMapping("/getRedis")
    public boolean getRedis(){
        return StringUtils.isNotBlank(stringRedisTemplate.opsForValue().get("testExpireKey"));
    }
}
