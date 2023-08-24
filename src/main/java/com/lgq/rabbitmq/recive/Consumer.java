package com.lgq.rabbitmq.recive;

import com.lgq.sharding.entity.Student;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName: Consumer
 * @author: luogongquan
 * @since: 2023/7/18 11:25
 */
@Component
public class Consumer {
    @RabbitListener(queuesToDeclare = @Queue("rabbitmq_queue"))
    public void process(Student message) {
        System.out.println("消费者消费消息111=====" + message);
    }
}
