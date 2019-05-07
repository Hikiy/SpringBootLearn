package com.hiki.springbootlearn;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringbootlearnApplication {
    @Value("${learn.author}")
    private String learnAuthor;
    @Value("${learn.name}")
    private String learnName;

    @RequestMapping("/")
    String index(){
        return "learn name is:"+learnName+",and learn author is:"+learnAuthor;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootlearnApplication.class, args);
    }

}
