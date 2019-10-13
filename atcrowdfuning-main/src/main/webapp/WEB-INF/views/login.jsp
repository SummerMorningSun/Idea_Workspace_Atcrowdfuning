<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
    <%@ include file="/WEB-INF/include/base_css.jsp" %>

    <style>

    </style>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div><a class="navbar-brand" href="index.html" style="font-size:32px;">尚筹网-创意产品众筹平台</a></div>
        </div>
    </div>
</nav>

<div class="container">

    <form class="form-signin" role="form" method="post">
        <h2 class="form-signin-heading"><i class="glyphicon glyphicon-log-in"></i> 用户登录</h2>
        <span>${requestScope.errorMsg }</span>
        <div class="form-group has-success has-feedback">
            <!-- 提交的表单项的name值 一般和bean的属性名一样  -->
            <input type="text" class="form-control" name="loginacct" id="inputSuccess4" placeholder="请输入登录账号" autofocus>
            <span class="glyphicon glyphicon-user form-control-feedback"></span>
        </div>
        <div class="form-group has-success has-feedback">
            <input type="text" class="form-control" name="userpswd" id="inputSuccess4" placeholder="请输入登录密码" style="margin-top:10px;">
            <span class="glyphicon glyphicon-lock form-control-feedback"></span>
        </div>
        <div class="form-group has-success has-feedback">
            <select class="form-control" >
                <option value="member">会员</option>
                <option value="user">管理</option>
            </select>
        </div>
        <div class="checkbox">
            <label>
                <input type="checkbox" value="remember-me"> 记住我
            </label>
            <br>
            <label>
                忘记密码
            </label>
            <label style="float:right">
                <a href="reg.html">我要注册</a>
            </label>
        </div>
        <a class="btn btn-lg btn-success btn-block" onclick="dologin()" > 登录</a>
    </form>
</div>
<!--
    src： img、js引入、iframe引入其他页面     代表引入，window.onload会等待标签和scr引入的资源完成后才调用方法，代表文档加载完成
    href：a、link引入css样式    代表引用，window.onload不会等待href引用的资源加载完成
 -->
<%@ include file="/WEB-INF/include/base_js.jsp" %>
<script>
    function dologin() {
        var type = $(":selected").val();
        if ( type == "user" ) {
            // window.location.href = "main.html";
            $("form").prop("action" , "${PATH }/doLogin").submit();//prop():一般设置标签自带属性值  attr():一般设置自定义属性值
        } else {
            window.location.href = "index.html";
        }
    }
</script>
</body>
</html>