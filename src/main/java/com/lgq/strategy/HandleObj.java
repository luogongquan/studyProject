package com.lgq.strategy;

import org.springframework.stereotype.Component;

/**
 * @ClassName: HandleObj
 * @author: luogongquan
 * @since: 2023/7/31 18:35
 */
@Component
public class HandleObj extends Handle1{
    @Override
    public String getCode(){
        return "4002";
    }
    @Override
    protected void handle(){
        System.out.println("HandleObj");
    }
}
