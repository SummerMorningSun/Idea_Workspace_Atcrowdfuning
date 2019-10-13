package com.atguigu.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.atguigu.springboot.mapper") // 指定包扫描mapper【注意：范围越小越好】
public class SpringbootSsmDemo01Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSsmDemo01Application.class, args);
    }

}
