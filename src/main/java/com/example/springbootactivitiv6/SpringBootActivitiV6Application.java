package com.example.springbootactivitiv6;

import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SpringBootActivitiV6Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootActivitiV6Application.class, args);
        System.out.println("hello world!");
    }

}
