package com.hiki.springbootlearn.RabbitMQ;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue Queue(){
        //org.springframework.amqp.core.Queue;
        return new Queue("hello");
    }
}
