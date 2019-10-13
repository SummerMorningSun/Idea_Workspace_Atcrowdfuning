package com.atguigu.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class SpringbootSsmWeb01Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSsmWeb01Application.class, args);
    }

}
