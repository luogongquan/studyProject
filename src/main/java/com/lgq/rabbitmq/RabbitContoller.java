package com.lgq.rabbitmq;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: RabbitContoller
 * @author: luogongquan
 * @since: 2023/7/18 11:28
 */
@RestController
@RequestMapping("/rabbit")
public class RabbitContoller {
    @Autowired
    private Producer producer;

    @RequestMapping("/sendMsg")
    public void sendMsg(String msg){
        producer.produce(msg);
    }

    public static void main(String[] args) {
        DateTime parse = DateUtil.parse("2023-07-21 00:00:00", DatePattern.NORM_DATETIME_PATTERN);
        DateTime parse1 = DateUtil.parse("2023-07-25 23:59:59", DatePattern.NORM_DATETIME_PATTERN);
        System.out.println(DateUtil.betweenDay(parse, parse1, true));
    }

}
