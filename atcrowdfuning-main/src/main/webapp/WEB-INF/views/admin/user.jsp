<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <%@ include file="/WEB-INF/include/base_css.jsp" %>

    <style>
        .tree li {
            list-style-type: none;
            cursor: pointer;
        }

        table tbody tr:nth-child(odd) {
            background: #F4F4F4;
        }

        table tbody td:nth-child(even) {
            color: #C00;
        }
    </style>
</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 用户维护</a></div>
        </div>
        <%@ include file="/WEB-INF/include/manager_header.jsp" %>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <%@ include file="/WEB-INF/include/manager_menu.jsp" %>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="glyphicon glyphicon-th"></i> 数据列表</h3>
                </div>
                <div class="panel-body">
                    <form class="form-inline" action="${PATH}/admin/index.html" method="post" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input class="form-control has-success" name="condition" value="${param.condition}" type="text" placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button type="submit" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>
                    <button id="deleteAdminBtn" type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i
                            class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <button type="button" onclick="window.location='${PATH}/admin/add.html'" class="btn btn-primary" style="float:right;"
                            ><i class="glyphicon glyphicon-plus"></i> 新增
                    </button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <thead>
                                <tr>
                                    <th width="30">#</th>
                                    <th width="30"><input type="checkbox"></th>
                                    <th>账号</th>
                                    <th>名称</th>
                                    <th>邮箱地址</th>
                                    <th width="100">操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:choose>
                                    <c:when test="${empty pageInfo.list}">
                                        <tr>
                                            <td colspan="6">没有查询到管理员列表</td>
                                        </tr>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach items="${pageInfo.list}" var="admin" varStatus="vs">
                                            <tr>
                                                <td>${vs.count}</td>
                                                <td><input id="${admin.id}" type="checkbox"></td>
                                                <td>${admin.loginacct}</td>
                                                <td>${admin.username}</td>
                                                <td>${admin.email}</td>
                                                <td>
                                                    <button adminid="${admin.id}" type="button" class="btn btn-success btn-xs"><i
                                                            class=" glyphicon glyphicon-check"></i></button>
                                                    <button adminid="${admin.id}" type="button" class="btn btn-primary btn-xs"><i
                                                            class=" glyphicon glyphicon-pencil"></i></button>
                                                    <button adminid="${admin.id}" type="button" class="btn btn-danger btn-xs deleteAdminBtn"><i
                                                            class=" glyphicon glyphicon-remove"></i></button>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                            </tbody>
                            <tfoot>
                                <tr>
                                    <td colspan="6" align="center">
                                        <%--
                                            分页对象：PageInfo{pageNum=1, pageSize=2, size=2, startRow=1, endRow=2, total=10, pages=5,
                                            list= 分页admin的集合
                                                Page{count=true, pageNum=1, pageSize=2, startRow=0, endRow=2, total=10, pages=5, reasonable=false, pageSizeZero=false},
                                            prePage=0, nextPage=2, isFirstPage=true, isLastPage=false,
                                            hasPreviousPage=false, hasNextPage=true, navigatePages=3, navigateFirstPage1, navigateLastPage3,
                                            navigatepageNums=[1, 2, 3]} --%>
                                        <ul class="pagination">

                                            <c:choose>
                                                <c:when test="${pageInfo.hasPreviousPage}">
                                                    <%-- 有上一页 --%>
                                                    <li><a href="${PATH}/admin/index.html?pageNum=${pageInfo.pageNum-1}&condition=${param.condition}">上一页</a></li>
                                                </c:when>
                                                <c:otherwise>
                                                    <%-- 没有上一页 --%>
                                                    <li class="disabled"><a href="javascript:void(0);">上一页</a></li>
                                                </c:otherwise>
                                            </c:choose>

                                            <%-- 中间页码 --%>
                                            <c:forEach items="${pageInfo.navigatepageNums}" var="index">
                                                <c:choose>
                                                    <c:when test="${index==pageInfo.pageNum}">
                                                        <%-- 当前页码 --%>
                                                        <li class="active"><a href="javascript:void(0)">${index}<span class="sr-only">(current)</span></a></li>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <%-- 不是当前页码 --%>
                                                        <li><a href="${PATH}/admin/index.html?pageNum=${index}&condition=${param.condition}">${index}</a></li>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:forEach>

                                            <c:choose>
                                                <c:when test="${pageInfo.hasNextPage}">
                                                    <%-- 有下一页 点击时跳转到当前页码的下一页--%>
                                                    <li><a href="${PATH}/admin/index.html?pageNum=${pageInfo.pageNum+1}&condition=${param.condition}">下一页</a></li>
                                                </c:when>
                                                <c:otherwise>
                                                    <%-- 没有下一页 --%>
                                                    <li class="disabled"><a href="javascript:void(0);">下一页</a></li>
                                                </c:otherwise>
                                            </c:choose>

                                        </ul>
                                    </td>
                                </tr>
                            </tfoot>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/WEB-INF/include/base_js.jsp" %>
<script type="text/javascript">
    $(function () {
        $(".list-group-item").click(function () {
            if ($(this).find("ul")) {
                $(this).toggleClass("tree-closed");
                if ($(this).hasClass("tree-closed")) {
                    $("ul", this).hide("fast");
                } else {
                    $("ul", this).show("fast");
                }
            }
        });
    });
    $("tbody .btn-success").click(function () {
        var adminid = $(this).attr("adminid");
        window.location.href = "${PATH}/admin/assignRole.html?id="+adminid;
    });
    $("tbody .btn-primary").click(function () {
        var adminid = $(this).attr("adminid");
        window.location.href = "${PATH}/admin/edit.html?id="+adminid;
    });

    // 给删除按钮绑定单击事件，点击删除所在行的管理员（数据）
    $(".deleteAdminBtn").click(function () {
        // 获取要删除的管理员id
        // this代表被点击的按钮的dom对象.this.属性名只能获取标签自带属性的属性值
        var adminId = $(this).attr("adminid"); // prop()获取自带属性，attr获取自定义属性
        //console.log(adminId);
        // 弹出确认框
        layer.confirm("你确定删除么"+$(this).parents("tr").children("td:eq(3)").text()+"吗？",
            {title:"删除提示",icon:3},function (a) {
                //alert(adminId);
                layer.close(a);
                // 提交请求给服务器并上传id
                window.location = "${PATH}/admin/deleteAdmin?id="+adminId;
            })
    });
    // 全选全不选 点击事件

    // 1.全选框别点击时， 设置tbody内的所有的复选框选中状态和全选框一致
    $("table thead :checkbox").click(function () {
        var flag= this.checked; // 获取被点击的复选框的选中状态 true false
        $("table tbody :checkbox").prop("checked",flag)//设置别点击时同步选中状态
    });
    // 2.tbody内的复选框被点击是，应该判断是否全部被选中，如果全选了设置全选框选中
    $("table tbody :checkbox").click(function () {
        var totalCount = $("table tbody :checkbox").length;
        var checkedCount = $("table tbody :checkbox:checked").length;
        $("table thead :checkbox").prop("checked",totalCount==checkedCount);
    });
    $("#deleteAdminBtn").click(function () {
        // 获取被选中的要删除的管理员的id集合
        var $checkedInp = $("table tbody :checkbox:checked");
        // 创建一个js的数组来保存 管理员id集合
        // 凭借字符串参数来保存管理员id集合
        var ids = new Array();
        $.each($checkedInp,function () {
            //this代表正在遍历的元素的dom对象
            ids.push(this.id);
        });
        //将数组转为 参数字符串
        var idsStr = ids.join(",");
        //提交请求给服务器处理删除请求     ids=1,2,3,4;
        window.location = "${PATH}/admin/deleteAdmins?ids="+idsStr;
    });
</script>
<script type="text/javascript" src="${PATH}/static/include/admin_js.js"></script>
</body>
</html>

