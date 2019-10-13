package com.atguigu.atcrowdfunding.listener;

import com.atguigu.atcrowdfunding.consts.AppConsts;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author erdong
 * @create 2019-09-09 22:16
 */
public class AppStartupListenter implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // 设置项目名到servletContext域中
        sce.getServletContext().setAttribute(AppConsts.CONTEXT_PATH, sce.getServletContext().getContextPath());
        System.out.println("====================" + sce.getServletContext().getContextPath());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
