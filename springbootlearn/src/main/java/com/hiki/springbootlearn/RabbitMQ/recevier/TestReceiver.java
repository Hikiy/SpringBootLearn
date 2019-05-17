package com.hiki.springbootlearn.RabbitMQ.recevier;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "hello")
public class TestReceiver {
    @RabbitHandler
    public void handler(String message){
        System.out.println("Receiver:  You say :" + message);
    }
}
