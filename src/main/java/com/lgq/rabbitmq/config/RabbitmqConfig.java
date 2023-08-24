package com.lgq.rabbitmq.config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: RabbitmqConfig
 * @author: luogongquan
 * @since: 2023/7/18 11:55
 */
@Configuration
public class RabbitmqConfig {
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("routingExchange");
    }

    @Bean
    public Queue psFirstQueue() {
        return new Queue("psFirstQueue");
    }

    @Bean
    public Queue psSecondQueue() {
        return new Queue("psSecondQueue");
    }

    @Bean
    public Queue psThirdQueue() {
        return new Queue("psThirdQueue");
    }

    @Bean
    public Binding routingFirstBinding() {
        return BindingBuilder.bind(psFirstQueue()).to(directExchange()).with("psFirstQueue");
    }

    @Bean
    public Binding routingSecondBinding() {
        return BindingBuilder.bind(psSecondQueue()).to(directExchange()).with("psSecondQueue");
    }

    @Bean
    public Binding routingThirdBinding() {
        return BindingBuilder.bind(psThirdQueue()).to(directExchange()).with("psThirdQueue");
    }


}
