package com.hiki.springbootlearn.RabbitMQ;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue Queue(){
        //org.springframework.amqp.core.Queue;
        return new Queue("hello");
    }

    @Bean
    public Queue Queue2(){
        return new Queue("hello2");
    }

    @Bean
    public Queue Queue3(){
        return new Queue("object");
    }

    //Topic Exchange
    @Bean
    public Queue queueMessage(){
        return new Queue("topic.message");
    }
    @Bean
    //要记住，Spring的Bean是怎么生成的，所以名字也要起好，以便容器拿的时候能识别出来，
    // 这里的名字跟后面的绑定就是要用同一个名字，不然绑定无法Autowired
    //接收所有topic消息
    public Queue queueAll(){
        return new Queue("topic.all");
    }
    @Bean
    //按我的理解，这个exchange就像一个规则，sender使用这个规则，就会去跟这个规则绑定的key进行匹配
    //根据sender给的key，在绑定中寻找匹配的。
    public TopicExchange exchange(){
        return new TopicExchange("exchange");
    }
    //下面这两个Bean，第一个只匹配topickey.message,并发到topic.message。
    //第二个只要以topickey开头都匹配，并发到topic.all。
    //也就是所有都发到topic.all，而topic.message只接收topickey.message
    @Bean
    public Binding bindingExchangeMessage(Queue queueMessage,TopicExchange exchange){
        return BindingBuilder.bind(queueMessage).to(exchange).with("topickey.message");
    }
    @Bean
    public Binding bindingExchangeAll(Queue queueAll,TopicExchange exchange){
        return BindingBuilder.bind(queueAll).to(exchange).with("topickey.#");
    }
}
