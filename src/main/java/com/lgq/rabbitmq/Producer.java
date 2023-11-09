package com.lgq.rabbitmq;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.lgq.sharding.entity.Student;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName: Produce
 * @author: luogongquan
 * @since: 2023/7/18 11:24
 */
@Component
public class Producer {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    public void produce(String msg) {
        String message = new Date() + "Beijing";
        System.out.println("生产者产生消息=====" + msg);
        Student student = new Student();
        student.setTime(new Date());
        rabbitTemplate.convertAndSend("rabbitmq_queue", student);
    }


    public static void main(String[] args) {
        System.out.println(DateUtil.parse("2023-07-21 09:23:15", DatePattern.NORM_DATETIME_MS_PATTERN).getTime());
    }
}
