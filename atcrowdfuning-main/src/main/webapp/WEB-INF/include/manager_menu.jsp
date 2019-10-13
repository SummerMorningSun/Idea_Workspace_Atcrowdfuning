<%--
  Created by IntelliJ IDEA.
  User: erdong
  Date: 2019/9/11
  Time: 8:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" pageEncoding="UTF-8" %>
<%-- 左边的树菜单 --%>
<div class="tree">
    <ul style="padding-left:0px;" class="list-group">
        <c:forEach items="${parents}" var="parent">
            <c:choose>
                <c:when test="${empty parent.children }">
                    <%-- 没有子菜单 --%>
                    <li class="list-group-item tree-closed">
                        <a href="${PATH}/${parent.url}"><i class="${parent.icon}"></i> ${parent.name}</a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="list-group-item tree-closed">
                                    <span><i class="${parent.icon}"></i> ${parent.name}
                                        <span class="badge" style="float:right">${parent.children.size()}</span>
                                    </span>
                        <ul style="margin-top:10px;display:none;">
                                <%-- 遍历显示当前父菜单的子菜单集合 --%>
                            <c:forEach items="${parent.children}" var="child">
                                <li style="height:30px;">
                                    <a href="${PATH}/${child.url}"><i class="${child.icon}"></i>${child.name}</a>
                                </li>
                            </c:forEach>
                        </ul>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

    </ul>
</div>
