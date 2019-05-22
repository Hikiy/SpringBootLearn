package com.hiki.springbootlearn.RabbitMQ.recevier;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "topic.all")
public class TopicReceivers {

    @RabbitHandler
    public void receiver(String message){
        System.out.println("topic.all: " + message);
    }
}
