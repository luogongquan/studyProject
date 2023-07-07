package com.lgq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.TimeUnit;

@SpringBootApplication
@MapperScan({"com.lgq.sharding.mapper"})
public class RedisListenerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisListenerApplication.class, args);
    }


}
