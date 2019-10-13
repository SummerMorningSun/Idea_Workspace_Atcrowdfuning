package com.atguigu.springboot.helloworld;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author erdong
 * @create 2019-09-20 20:53
 */
@SpringBootApplication // 让当前类成为一个组件
public class MainApplication {

    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);
    }

}
