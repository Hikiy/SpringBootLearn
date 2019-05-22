package com.hiki.springbootlearn.RabbitMQ.sender;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TopicSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送给单信息
     * @param message
     */
    public void send1(String message){
        String context = "TopicSender: " + message;
        System.out.println(context);
        rabbitTemplate.convertAndSend("exchange","topickey.message",message);
    }

    public void send2(String message){
        String context =  "TopicSenders: " + message;
        System.out.println(context);
        rabbitTemplate.convertAndSend("exchange","topickey.all",message);
    }
}
