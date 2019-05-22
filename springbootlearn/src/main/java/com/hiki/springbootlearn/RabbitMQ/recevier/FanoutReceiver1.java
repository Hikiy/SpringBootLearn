package com.hiki.springbootlearn.RabbitMQ.recevier;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "fanout.1")
public class FanoutReceiver1 {
    @RabbitHandler
    public void receiver(String message){
        System.out.println("FanoutReceiver1收到广播： " + message);
    }
}
