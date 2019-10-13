package com.atguigu.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author erdong
 * @create 2019-09-22 16:41
 */
@EnableTransactionManagement // 启用声明式事物
@SpringBootApplication
@MapperScan(basePackages = "com.atguigu.springboot.mapper")
public class SpringbootSsmDemo02Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSsmDemo02Application.class, args);
    }

}
