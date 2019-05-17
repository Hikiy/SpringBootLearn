package com.hiki.springbootlearn.RabbitMQ.recevier;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@RabbitListener(queues = "hello2")
public class TestReceiver2 {
    @Autowired
    private RabbitTemplate rt;

    @RabbitHandler
    public void handle(String message){
        String context = message +  new Date();
        System.out.println("Receiver2: "+context);
    }
}
