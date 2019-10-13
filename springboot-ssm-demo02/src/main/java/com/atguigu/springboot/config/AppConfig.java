package com.atguigu.springboot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author erdong
 * @create 2019-09-22 14:08
 */
@Configuration
public class AppConfig {

    // 只需要解析得到配置文件中的数据库链接参数
    @Value("${spring.datasource.username}")
    String username;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.url}")
    String url;
    @Value("${spring.datasource.driver-class-name}")
    String driverClassName;


    // 配置duird连接池

    @Bean
    public DruidDataSource getDruidDataSource() throws SQLException {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setUrl(url);
        dataSource.setDriverClassName(driverClassName);
        // 监控防火墙
        dataSource.setFilters("stat , wall");
        return dataSource;
    }

    // 配置Druid的监控
    // 1.配置一个管理后的Servlet
    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        Map<String, String> initParams = new HashMap<>();
        initParams.put("loginUsername", "root");
        initParams.put("loginPassword", "root");
        initParams.put("allow", ""); // 默认就是允许所有访问
        initParams.put("deny", "192.168.19.215"); // 拒绝那个ip访问
        bean.setInitParameters(initParams);
        return bean;
    }

    // 2.配置一个web监控的filter
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());
        Map<String, String> initParams = new HashMap<>();
        initParams.put("exclusions", "*.js,*.css,*.jpg,/druid/*"); // ***排除过滤***
        bean.setInitParameters(initParams);
        bean.setUrlPatterns(Arrays.asList("/*"));
        return bean;
    }

}
