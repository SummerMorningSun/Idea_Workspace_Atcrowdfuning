<%--
  Created by IntelliJ IDEA.
  User: erdong
  Date: 2019/9/11
  Time: 8:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" %>
<%-- 引入SpringSecuript标签库 --%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<div id="navbar" class="navbar-collapse collapse">
    <ul class="nav navbar-nav navbar-right">
        <li style="padding-top:8px;">
            <div class="btn-group">
                <button type="button" class="btn btn-default btn-success dropdown-toggle"
                        data-toggle="dropdown">
                    <i class="glyphicon glyphicon-user"></i> <security:authentication property="name"/> <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" role="menu">
                    <li><a href="#"><i class="glyphicon glyphicon-cog"></i> 个人设置</a></li>
                    <li><a href="#"><i class="glyphicon glyphicon-comment"></i> 消息</a></li>
                    <li class="divider"></li>
                    <li><a id="logoutBtn" href="${PATH}/logout"><i class="glyphicon glyphicon-off"></i>
                        退出系统</a></li>
                </ul>
            </div>
        </li>
        <%-- 添加SpringSecurity细粒度 --%>
        <li style="margin-left:10px;padding-top:8px;">
            <button type="button" class="btn btn-default btn-danger">
                <span class="glyphicon glyphicon-question-sign"></span> 帮助
            </button>
            <security:authorize access="hasAnyRole('SE - 软件工程师')">
                <button type="button" class="btn btn-default btn-danger">
                    <span class="glyphicon glyphicon-question-sign"></span> SE - 软件工程师
                </button>
            </security:authorize>
        </li>
    </ul>
    <form class="navbar-form navbar-right">
        <input type="text" class="form-control" placeholder="查询">
    </form>
</div>
<script type="text/javascript">
    $("#logoutBtn").click(function () {
        var url = this.href;
        layer.confirm("你真的要注销么？", {icon: 2, title: "退出提示"}, function (index) {
            layer.close(index);
            layer.msg("注销中...", {icon: 16, time: 215});
            setTimeout(function () {
                window.location = url;
            }, 215);
        });
        return false;
    });
</script>