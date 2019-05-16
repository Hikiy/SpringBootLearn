## 目录
### [spring.profiles.active配置多环境失败](#1)
### [配置Mybatis XML模式后报错“Unsatisfied dependency expressed through bean property 'sqlSessionFactory' ”](#2)

### <span id="1">spring.profiles.active配置多环境失败</span>  
原因为在spring.profiles.active=dev 后面加了注释。。不能写注释，会报错  

###<span id="2">配置Mybatis XML模式后报错“Unsatisfied dependency expressed through bean property 'sqlSessionFactory' ”</span>
原因为xml配置文件都忘了加头：
```
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN" "http://mybatis.org/dtd/mybatis-3-config.dtd">
```