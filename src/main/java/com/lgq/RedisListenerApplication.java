package com.lgq;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan({"com.lgq.sharding.mapper"})
public class RedisListenerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisListenerApplication.class, args);
    }

}
