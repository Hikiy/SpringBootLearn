## 目录
### [spring.profiles.active配置多环境失败](#1)
### [配置Mybatis XML模式后报错“Unsatisfied dependency expressed through bean property 'sqlSessionFactory' ”](#2)
### [配置Mybatis多数据源时报错：Error creating bean with name 'entityManagerFactory'...java.lang.IllegalArgumentException: jdbcUrl is required with driverClassName.](#3)

### <span id="1">spring.profiles.active配置多环境失败</span>  
原因为在spring.profiles.active=dev 后面加了注释。。不能写注释，会报错  

### <span id="2">配置Mybatis XML模式后报错“Unsatisfied dependency expressed through bean property 'sqlSessionFactory' ”</span>
原因为xml配置文件都忘了加头：
```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
```

###  <span id="3">配置Mybatis多数据源时报错：Error creating bean with name 'entityManagerFactory'...java.lang.IllegalArgumentException: jdbcUrl is required with driverClassName.</span>
在配置Mybatis多数据源后报错了，经过长时间debug，在网上看到了配置文件和我的配置文件有些不同，我的配置文件是：
```
spring.datasource.test1.url=jdbc:mysql://localhost:3306/springboot?serverTimezone=GMT&useSSL=false
spring.datasource.test1.username=root
spring.datasource.test1.password=123456
spring.datasource.test1.driver-class-name=com.mysql.cj.jdbc.Driver

spring.datasource.test2.url=jdbc:mysql://localhost:3306/springbootmybatis?serverTimezone=GMT&useSSL=false
spring.datasource.test2.username=root
spring.datasource.test2.password=123456
spring.datasource.test2.driver-class-name=com.mysql.cj.jdbc.Driver
```
**解决方案**  
将url改成jdbc-url:
```
spring.datasource.test1.jdbc-url=jdbc:mysql://localhost:3306/springboot?serverTimezone=GMT&useSSL=false
spring.datasource.test1.username=root
spring.datasource.test1.password=123456
spring.datasource.test1.driver-class-name=com.mysql.cj.jdbc.Driver

spring.datasource.test2.jdbc-url=jdbc:mysql://localhost:3306/springbootmybatis?serverTimezone=GMT&useSSL=false
spring.datasource.test2.username=root
spring.datasource.test2.password=123456
spring.datasource.test2.driver-class-name=com.mysql.cj.jdbc.Driver
```
官方文档的解释：  
> 因为连接池的实际类型没有被公开，所以在您的自定义数据源的元数据中没有生成密钥，而且在IDE中没有完成(因为DataSource接口没有暴露属性)。另外，如果您碰巧在类路径上有Hikari，那么这个基本设置就不起作用了，因为Hikari没有url属性(但是确实有一个jdbcUrl属性)。在这种情况下，您必须重写您的配置
