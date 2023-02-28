package com.junda.service01;

//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
//@ComponentScan("com.junda")
//@MapperScan("com.junda.**.mapper")
public class Service01Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext application = SpringApplication.run(Service01Application.class, args);
        ConfigurableEnvironment env = application.getEnvironment();
        String port = env.getProperty("server.port");
        String path = env.getProperty("server.servlet.context-path");
        System.out.println("--------------------------------------");
        System.out.println("当前微服务是====Service01Application微服务");
        System.out.println("本地api访问地址:http://localhost:" + port + path);
        System.out.println("--------------------------------------");
    }

}
