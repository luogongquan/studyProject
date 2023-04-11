package com.lgq.redislistener.redis.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Author: luogongquan
 * @date: 2023/4/7 15:10
 * @Description:
 **/
public class MyServletContextListener implements ServletContextListener {
    private ScheduledExecutorService scheduler;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        // 在Web应用程序启动时执行的操作
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(new DataUpdater(event.getServletContext()), 0, 1, TimeUnit.MINUTES);
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // 在Web应用程序关闭时执行的操作
        scheduler.shutdownNow();
    }
}
