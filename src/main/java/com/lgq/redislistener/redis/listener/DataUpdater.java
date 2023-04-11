package com.lgq.redislistener.redis.listener;

import javax.servlet.ServletContext;

/**
 * @Author: luogongquan
 * @date: 2023/4/7 15:12
 * @Description:
 **/
public class DataUpdater implements Runnable{
    private ServletContext context;
    public DataUpdater(ServletContext context){
        this.context=context;
    }
    @Override
    public void run() {
        System.out.println("1111122222========");
    }
}
