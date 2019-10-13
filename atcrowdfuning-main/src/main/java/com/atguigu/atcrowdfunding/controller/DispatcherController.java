package com.atguigu.atcrowdfunding.controller;

import com.atguigu.atcrowdfuning.mapper.TMemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author erdong
 * @create 2019-09-09 1:26
 */
@Controller
public class DispatcherController {

    @Autowired
    TMemberMapper memberMapper;

    // 登录页面的跳转
    @RequestMapping("/login.html")
    public String toLoginPage() {
        return "login";
    }


    //请求首页
    @RequestMapping(value = {"/", "/index", "/index.html","/static/index.html"})
    public String toIndexPage() {
        System.out.println("=============" + memberMapper);
        return "index";
    }

}
