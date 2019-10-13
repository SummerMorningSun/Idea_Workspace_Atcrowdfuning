package com.atguigu.springboot.helloworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author erdong
 * @create 2019-09-20 20:56
 */
@Controller
public class HelloWorldController {

    @ResponseBody
    @RequestMapping("/hello")
    public String hello() {
        return "hello,第一个Springboot";
    }

}
