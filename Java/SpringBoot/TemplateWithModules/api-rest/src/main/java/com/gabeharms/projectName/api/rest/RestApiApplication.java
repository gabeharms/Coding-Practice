package com.gabeharms.projectName.api.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.gabeharms.projectName")
public class RestApiApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(RestApiApplication.class, args);
    }

}
