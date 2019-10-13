package com.atguigu.atcrowdfunding.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfuning.bean.TPermission;
import com.atguigu.atcrowdfunding.service.TPermissionService;

@Controller
public class TPermissionController {

    @Autowired
    TPermissionService permissionService;

    // 跳转到许可权限管理页面
    @RequestMapping("/permission/index.html")
    public String toPermissions(){
        return "permission/permission";
    }

    //处理查询所有权限列表的异步请求
    @ResponseBody
    @RequestMapping("/permission/getPermissions")
    public List<TPermission> getPermissions() {
        return permissionService.listAllPermissions();
    }

}
