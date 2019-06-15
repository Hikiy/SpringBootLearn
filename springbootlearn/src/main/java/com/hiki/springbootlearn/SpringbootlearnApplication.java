package com.hiki.springbootlearn;

import com.hiki.springbootlearn.entity.AuthorSettings;
import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
//@MapperScan("com.hiki.springbootlearn.mapper")
//@MapperScan("com.hiki.springbootlearn.mybatis.mapper")
@EnableScheduling
//定时开启
public class SpringbootlearnApplication {
    @Value("${learn.author}")
    private String learnAuthor;
    @Value("${learn.name}")
    private String learnName;
    @Autowired
    private AuthorSettings authorSettings;

    @RequestMapping("/")
    String index(){
        return "learn name is:"+learnName+",and learn author is:"+learnAuthor;
    }

    @RequestMapping("/authorsetting")
    String authorsetting(){
        return "name is "+authorSettings.getName()+" age is "+authorSettings.getAge();
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringbootlearnApplication.class, args);
    }

    //以下为tomcat配置
//    @Bean
//    public TomcatServletWebServerFactory servletContainer() {
//        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
//            @Override
//            protected void postProcessContext(Context context) {
//                SecurityConstraint constraint = new SecurityConstraint();
//                constraint.setUserConstraint("CONFIDENTIAL");
//                SecurityCollection collection = new SecurityCollection();
//                collection.addPattern("/*");
//                constraint.addCollection(collection);
//                context.addConstraint(constraint);
//            }
//        };
//        tomcat.addAdditionalTomcatConnectors(httpConnector());
//        return tomcat;
//    }
//    @Bean
//    public Connector httpConnector() {
//        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        connector.setScheme("http");
//        //Connector监听的http的端口号
//        connector.setPort(8321);
//        connector.setSecure(false);
//        //监听到http的端口号后转向到的https的端口号
//        connector.setRedirectPort(8432);
//        return connector;
//    }
}
