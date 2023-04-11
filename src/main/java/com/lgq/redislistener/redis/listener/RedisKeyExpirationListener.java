package com.lgq.redislistener.redis.listener;

import lombok.extern.log4j.Log4j2;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * @Author: luogongquan
 * @date: 2023/4/7 14:39
 * @Description:
 **/
/*
@Log4j2
@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {
    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }
    @Override
    public void onMessage(Message message, byte[] pattern) {
        //获取过期的key
        String expireKey = message.toString();
        System.out.println("终于失效了");
        log.debug("key is:"+ expireKey);
        System.out.println(expireKey);
        //这里还可以根据key的自定义前缀来判断执行哪个条件
        //......
    }

}*/
