package com.hiki.springbootlearn.rabbitMQ;

import com.hiki.springbootlearn.RabbitMQ.sender.TestSender;
import com.hiki.springbootlearn.RabbitMQ.sender.TestSender2;
import com.hiki.springbootlearn.RabbitMQ.sender.TestSender3;
import com.hiki.springbootlearn.entity.Users;
import org.junit.Ignore;
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
    @Autowired
    private TestSender2 ts2;
    @Autowired
    private TestSender3 ts3;

    @Test
    @Ignore
    public void hello() throws Exception{
        ts.send("Hello,Hiki");
    }

    @Test
    @Ignore
    //一对多测试,结果为轮流分配给Receiver
    public void one2many()throws Exception{
        for (int i=0;i<20;i++){
            ts2.send(i + ": Hello,Hiki");
        }
    }

    @Test
    @Ignore
    //多对多测试，结果也为轮流分配给Receiver，也就是怎么操作都是一个队列，sender发一个，receiver也只能拿一个
    public void many2many() throws  Exception{
        for (int i=0;i<20;i++){
            ts2.send(i + ": Hello,Hiki");
            ts3.send(i + ": Hello,Hiki2");
        }
    }

    @Test
    //测试传对象，可以直接传对象
    public void testObject() throws Exception{
        Users user = new Users();
        user.setAge(22L);
        user.setPassword("123456");
        user.setId(1L);
        user.setName("hiki");
        System.out.println(user);
        ts.sendObject(user);
    }
}
