package com.atguigu.springboot.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author erdong
 * @create 2019-09-22 19:03
 */
@WebServlet(urlPatterns = "/AServlet")
public class AServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().write("<script>alert('心底善良，优雅从容')</script>");
    }

}
