package com.atguigu.atcrowdfunding.config;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author erdong
 * @create 2019-09-17 22:11
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.authorizeRequests()
                // 首页和静态资源授权所有人都可以访问
                .antMatchers("/", "/index", "/static/**", "/index.html").permitAll()
                // 其他请求都需要认证 【其他的资源必须认证】
                .anyRequest().authenticated();

        // 设置和登录相关的配置
        http.formLogin()
                // 指定登录页面 并且设置permitAll()授权所有人都可以访问[否则访问未授权页面时跳向登录页面 死循环]
                .loginPage("/login.html").permitAll()
                // 指定登录请求参数获取的key
                .usernameParameter("loginacct")
                // 指定登录请求参数 密码的key
                .passwordParameter("userpswd")
                // 指定springSecurity处理登录请求的url，必须和登录页面提交的post方式的登录请求url一样
                .loginProcessingUrl("/doLogin")
                // 指定登录成功后跳转的页面地址
                .defaultSuccessUrl("/main.html")

                // 设置和退出相关的配置
                .and()
                .logout()
                // 指定SpringSecurity处理退出请求的url，必须和退出跳转的请求的url一样
                .logoutUrl("/logout")
                // 指定退出成功后跳转的页面地址
                .logoutSuccessUrl("/main.html")
                // 退出是要删除的cookies的名字
                .deleteCookies("JSESSIONID");

        // 无权限访问页面时的异常处理
        http.exceptionHandling().accessDeniedHandler(new AccessDeniedHandler() {
            @Override
            public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
                // 异步请求访问服务器时，需要另外处理，给异步状态码即可
                // 如果是异步请求：请求头会多出参数X-Requested-With:XMLHttpRequest
                if ("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
                    // 异步请求
                    Map<String, String> map = new HashMap<>();
                    map.put("code", 403 + ""); // 转为字符串传给前端js判断
                    map.put("errorMsg", accessDeniedException.getMessage());
                    map.put("resource",request.getServletPath());// 访问失败的资源
                    String jsonString = JSON.toJSONString(map);
                    response.getWriter().write(jsonString);
                } else {
                    // 处理同步请求无权访问页面的异常
                    request.setAttribute("resource", request.getServletPath());// 访问失败的资源
                    request.setAttribute("errorMsg", accessDeniedException.getMessage()); // 错误提示
                    request.getRequestDispatcher("/WEB-INF/views/error/403.jsp").forward(request, response);
                }
            }
        });

        // 禁用CSRF
        http.csrf().disable();
    }

    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    PasswordEncoder passwordEncoder;

    // 主体授权
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //super.configure(auth);
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);

    }
}
