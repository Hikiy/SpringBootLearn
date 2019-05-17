package com.hiki.springbootlearn.RabbitMQ.recevier;

import com.hiki.springbootlearn.entity.Users;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "object")
public class ObjectReceiver {

    @RabbitHandler
    public void handler(Users object){
        System.out.println("ObjectReceiver:"+object);
    }
}
