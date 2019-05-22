package com.hiki.springbootlearn.RabbitMQ.recevier;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "topic.message" )
public class TopicReceiver {
    @RabbitHandler
    public void receive(String message){
        System.out.println("topic.message: "+message);
    }
}
