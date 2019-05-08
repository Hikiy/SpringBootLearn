# Web综合开发
### Json接口开发
在类添加@RestController方法即可，类中的方法默认返回Json  
如果需要使用页面开发则使用@Controller

### 自定义Filter
我们常常在项目中会使用filters用于录调用日志、排除有XSS威胁的字符、执行权限验证等等。Spring Boot自动添加了OrderedCharacterEncodingFilter和HiddenHttpMethodFilter，并且我们可以自定义Filter。

两个步骤：
- 实现Filter接口，实现Filter方法
- 添加@Configurationz 注解，将自定义Filter加入过滤链

上代码
```
@Configurationpublic class WebConfiguration {
    @Bean
    public RemoteIpFilter remoteIpFilter() {
        return new RemoteIpFilter();
    }    
    @Bean
    public FilterRegistrationBean testFilterRegistration() {

        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new MyFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("paramName", "paramValue");
        registration.setName("MyFilter");
        registration.setOrder(1);        return registration;
    }    
    
    public class MyFilter implements Filter {
        @Override
        public void destroy() {           
         // TODO Auto-generated method stub
        }        
         
        @Override
        public void doFilter(ServletRequest srequest, ServletResponse sresponse, FilterChain filterChain)
                throws IOException, ServletException {            
            // TODO Auto-generated method stub
            HttpServletRequest request = (HttpServletRequest) srequest;
            System.out.println("this is MyFilter,url :"+request.getRequestURI());
            filterChain.doFilter(srequest, sresponse);
        }        
        @Override
        public void init(FilterConfig arg0) throws ServletException {
           // TODO Auto-generated method stub
        }
    }
}
```

### 自定义Property
配置在application.properties中
```
com.hiki.title=hiki
com.hiki.description=px
```
自定义配置类
```
@Componentpublic class NeoProperties {
    @Value("${com.hiki.title}")
    private String title;    
    @Value("${com.hiki.description}")    
    private String description;      
    //省略getter settet方法
    }
```
**log配置**  
配置输出的地址和输出级别  
```
logging.path=/user/local/log
logging.level.com.favorites=DEBUG
logging.level.org.springframework.web=INFO
logging.level.org.hibernate=ERROR
```
path为本机的log地址，logging.level 后面可以根据包路径配置不同资源的log级别

### 数据库操作
jpa是利用Hibernate生成各种自动化的sql，如果只是简单的增删改查，基本上不用手写了，spring内部已经帮大家封装实现了。

**如何在spring boot中使用**
- 1.添加相jar包
```
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
     <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>
```
- 2.添加配置文件
```
spring.datasource.url=jdbc:mysql://localhost:3306/test?serverTimezone=GMT&useSSL=false(给JDBC的URL指定时区,指定SSL是true或者false)
spring.datasource.username=root
spring.datasource.password=root
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver(JDK9以后连接MYSQL数据库会报错，是因为sql语句改变了。把com.mysql.jdbc.Driver  替换成com.mysql.cj.jdbc.Driver)

spring.jpa.properties.hibernate.hbm2ddl.auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
spring.jpa.show-sql= true
```
其实这个hibernate.hbm2ddl.auto参数的作用主要用于：自动创建|更新|验证数据库表结构,有四个值：  
- create： 每次加载hibernate时都会删除上一次的生成的表，然后根据你的model类再重新来生成新表，哪怕两次没有任何改变也要这样执行，这就是导致数据库表数据丢失的一个重要原因。  
- create-drop ：每次加载hibernate时根据model类生成表，但是sessionFactory一关闭,表就自动删除。  
- update：最常用的属性，第一次加载hibernate时根据model类会自动建立起表的结构（前提是先建立好数据库），以后加载hibernate时根据 model类自动更新表结构，即使表结构改变了但表中的行仍然存在不会删除以前的行。要注意的是当部署到服务器后，表结构是不会被马上建立起来的，是要等 应用第一次运行起来后才会。
- validate ：每次加载hibernate时，验证创建数据库表结构，只会和数据库中的表进行比较，不会创建新表，但是会插入新值。
- dialect 主要是指定生成表名的存储引擎为InneoDB
- show-sql 是否打印出自动生产的SQL，方便调试的时候查看  


- 3.添加实体类和Dao
```
@Entitypublic class User implements Serializable { 
    private static final long serialVersionUID = 1L;    
    
    @Id
    @GeneratedValue
    private Long id;    
    @Column(nullable = false, unique = true)    
    private String userName;    
    @Column(nullable = false)    
    private String passWord;    
    @Column(nullable = false, unique = true)    
    private String email;    
    @Column(nullable = true, unique = true)    
    private String nickName;    
    @Column(nullable = false)    
    private String regTime;    
    
    //省略getter settet方法、构造方法}
```
dao只要继承JpaRepository类就可以，几乎可以不用写方法，还有一个特别有尿性的功能非常赞，就是可以根据方法名来自动的生产SQL，比如findByUserName 会自动生产一个以 userName 为参数的查询方法，比如 findAlll   自动会查询表里面的所有数据，比如自动分页等等。。  
Entity中不映射成列的字段得加@Transient 注解，不加注解也会映射成列
```
public interface UserRepository extends JpaRepository<User, Long> { 
   User findByUserName(String userName);    
   User findByUserNameOrEmail(String username, String email);
   }
```
- 4、测试
```
@RunWith(SpringJUnit4ClassRunner.class)@SpringApplicationConfiguration(Application.class)public class UserRepositoryTests {    @Autowired
    private UserRepository userRepository;   
     @Test
    public void test() throws Exception {
        Date date = new Date();
        DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG);        
        String formattedDate = dateFormat.format(date);

        userRepository.save(new User("aa1", "aa@126.com", "aa", "aa123456",formattedDate));
        userRepository.save(new User("bb2", "bb@126.com", "bb", "bb123456",formattedDate));
        userRepository.save(new User("cc3", "cc@126.com", "cc", "cc123456",formattedDate));

        Assert.assertEquals(9, userRepository.findAll().size());
        Assert.assertEquals("bb", userRepository.findByUserNameOrEmail("bb", "cc@126.com").getNickName());
        userRepository.delete(userRepository.findByUserName("aa1"));
    }

}
```
当让 spring data jpa 还有很多功能，比如封装好的分页，可以自己定义SQL，主从分离等等，这里就不详细讲了

### Thymeleaf 介绍
Thymeleaf是一款用于渲染XML/XHTML/HTML5内容的模板引擎。类似JSP，Velocity，FreeMaker等，它也可以轻易的与Spring MVC等Web框架进行集成作为Web应用的模板引擎。与其它模板引擎相比，Thymeleaf最大的特点是能够直接在浏览器中打开并正确显示模板页面，而不需要启动整个Web应用。
Thymeleaf是与众不同的，因为它使用了自然的模板技术。这意味着Thymeleaf的模板语法并不会破坏文档的结构，模板依旧是有效的XML文档。模板还可以用作工作原型，Thymeleaf会在运行期替换掉静态值。Velocity与FreeMarker则是连续的文本处理器。

下面的代码示例分别使用Velocity、FreeMarker与Thymeleaf打印出一条消息：
```
Velocity: <p>$message</p>
FreeMarker: <p>${message}</p>
Thymeleaf: <p th:text="${message}">Hello World!</p>
```
注意，由于Thymeleaf使用了XML DOM解析器，因此它并不适合于处理大规模的XML文件。

**WebJars**
WebJars是一个很神奇的东西，可以让大家以jar包的形式来使用前端的各种框架、组件。

**什么是WebJars**
什么是WebJars？WebJars是将客户端（浏览器）资源（JavaScript，Css等）打成jar包文件，以对资源进行统一依赖管理。WebJars的jar包部署在Maven中央仓库上。

**为什么使用**
我们在开发Java web项目的时候会使用像Maven，Gradle等构建工具以实现对jar包版本依赖管理，以及项目的自动化管理，但是对于JavaScript，Css等前端资源包，我们只能采用拷贝到webapp下的方式，这样做就无法对这些资源进行依赖管理。那么WebJars就提供给我们这些前端资源的jar包形势，我们就可以进行依赖管理。

**如何使用**
- WebJars主官网 查找对于的组件，比如Vuejs
```
<dependency>
    <groupId>org.webjars.bower</groupId>
    <artifactId>vue</artifactId>
    <version>1.0.21</version>
</dependency>
```
- 页面引入
```
<link th:href="@{/webjars/bootstrap/3.3.6/dist/css/bootstrap.css}" rel="stylesheet"></link>
```
就可以正常使用了！