package com.hiki.springbootlearn.RabbitMQ.sender;

import com.hiki.springbootlearn.entity.Users;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TestSender {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    public void send(String message){
        String context = message + "  " + new Date();
        System.out.println("Sender:" + context);
        this.rabbitTemplate.convertAndSend("hello",context);
    }

    public void sendObject(Users object){
        this.rabbitTemplate.convertAndSend("object",object);
    }
}
