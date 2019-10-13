package com.atguigu.springboot.springbootdemo01;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author erdong
 * @create 2019-09-20 22:02
 */
@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "hello,这是我的第二个springboot";
    }

    @RequestMapping("/tohello")
    public String toHello(){
        return "hello";
    }

}
