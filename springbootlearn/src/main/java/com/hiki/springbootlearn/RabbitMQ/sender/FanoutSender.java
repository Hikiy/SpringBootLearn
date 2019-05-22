package com.hiki.springbootlearn.RabbitMQ.sender;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FanoutSender {
    @Autowired
    private RabbitTemplate rt;

    public void send(String message){
        System.out.println("发送广播啦：" + message);
        rt.convertAndSend("fanoutExchange","",message);
    }
}
