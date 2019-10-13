package com.atguigu.atcrowdfunding.controller;

import com.atguigu.atcrowdfunding.service.TMenuService;
import com.atguigu.atcrowdfuning.bean.TMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author erdong
 * @create 2019-09-17 8:12
 */
@Controller
public class TMenuController {
    @Autowired
    TMenuService menuService;

    //处理修改菜单的异步请求
    @ResponseBody
    @RequestMapping("/menu/updateMenu")
    public String updateMenu(TMenu menu) {
        menuService.updateMenu(menu);
        return "ok";
    }

    //处理查询菜单异步请求
    @ResponseBody
    @RequestMapping("/menu/getMenu")
    public TMenu getMenu(Integer id) {
        TMenu menu = menuService.getMenu(id);
        return menu;
    }

    //处理添加菜单异步请求
    @ResponseBody
    @RequestMapping("/menu/saveMenu")
    public String saveMenu(TMenu menu) {// icon 、 name、pid
        menuService.saveMenu(menu);
        return "ok";
    }

    //处理删除指定菜单异步请求
    @ResponseBody
    @RequestMapping("/menu/deleteMenu")
    public String deleteMenu(Integer id) {
        menuService.deleteMenu(id);
        return "ok";
    }

    //处理查询菜单集合的异步请求
    @PreAuthorize("hasAnyRole('SE - 软件工程师')")
    @ResponseBody
    @RequestMapping("/menu/listMenus")
    public List<TMenu> listMenus() {
        List<TMenu> menus = menuService.listMenus();
        return menus;
    }

    // 跳转到menu  树显示页面
    @RequestMapping("/menu/index.html")
    public String toMenuPage() {
        return "menu/menu";
    }

}
