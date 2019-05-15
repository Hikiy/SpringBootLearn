package com.hiki.springbootlearn;

import com.hiki.springbootlearn.entity.AuthorSettings;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@MapperScan("com.hiki.springbootlearn.mapper")
public class SpringbootlearnApplication {
//    @Value("${learn.author}")
//    private String learnAuthor;
//    @Value("${learn.name}")
//    private String learnName;
    @Autowired
    private AuthorSettings authorSettings;

//    @RequestMapping("/")
//    String index(){
//        return "learn name is:"+learnName+",and learn author is:"+learnAuthor;
//    }

    @RequestMapping("/authorsetting")
    String authorsetting(){
        return "name is "+authorSettings.getName()+" age is "+authorSettings.getAge();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootlearnApplication.class, args);
    }

}
