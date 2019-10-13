package com.atguigu.springboot.controller;

import com.atguigu.springboot.service.TAdminService;
import com.atguigu.springboot.bean.TAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author erdong
 * @create 2019-09-21 8:42
 */
@Controller
public class TAdminController {

    @Autowired
    TAdminService tAdminService;

    @Autowired
    DataSource dataSource;

    @RequestMapping("/hello")
    public String listAdmins() {
        List<TAdmin> list = tAdminService.listAdmins();
        System.out.println(list);
        /**
         * springboot 默认提供的class com.zaxxer.hikari.HikariDataSource使用
         * 虽然springgboot默认提供的链接数据库驱动HikariDataSource本身性能确实也不低，甚至高与Druid
         * 但是我们还是推荐使用Druid,因为阿里巴巴的Druid功能更强大一些
         */

        System.out.println("=========" + dataSource.getClass());
        return "admin/admin";
    }

}
