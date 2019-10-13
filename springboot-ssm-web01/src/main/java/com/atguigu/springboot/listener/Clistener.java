package com.atguigu.springboot.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author erdong
 * @create 2019-09-22 19:26
 */
public class Clistener implements ServletContextListener {

    // 当项目关闭时，调用此方法，全局上下文对象销毁前调用此方法（持久化保存数据）
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("启动销毁方法");
    }

    // 全局上下文对象创建后调用此方法
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("启动初始化方法");
    }
}
