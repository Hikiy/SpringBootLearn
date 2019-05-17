package com.hiki.springbootlearn.rabbitMQ;

import com.hiki.springbootlearn.RabbitMQ.sender.TestSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRabbitMQ {
    @Autowired
    private TestSender ts;

    @Test
    public void hello() throws Exception{
        ts.send("Hello,Hiki");
    }
}
