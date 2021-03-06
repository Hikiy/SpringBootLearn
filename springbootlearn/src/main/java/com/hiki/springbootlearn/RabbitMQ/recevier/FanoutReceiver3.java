package com.hiki.springbootlearn.RabbitMQ.recevier;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "fanout.3")
public class FanoutReceiver3 {
    @RabbitHandler
    public void receiver(String message){
        System.out.println("FanoutReceiver3收到广播： " + message);
    }
}