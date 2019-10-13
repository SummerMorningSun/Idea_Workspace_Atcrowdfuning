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
            <div><a class="navbar-brand" style="font-size:32px;" href="#">众筹平台 - 角色维护</a></div>
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
                    <form class="form-inline" role="form" style="float:left;">
                        <div class="form-group has-feedback">
                            <div class="input-group">
                                <div class="input-group-addon">查询条件</div>
                                <input name="condition" class="form-control has-success" type="text"
                                       placeholder="请输入查询条件">
                            </div>
                        </div>
                        <button type="button" id="queryRolesBtn" class="btn btn-warning"><i
                                class="glyphicon glyphicon-search"></i> 查询
                        </button>
                    </form>
                    <button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i
                            class=" glyphicon glyphicon-remove"></i> 删除
                    </button>
                    <button type="button" id="saveRoleModelBtn" class="btn btn-primary" style="float:right;"><i
                            class="glyphicon glyphicon-plus"></i> 新增
                    </button>
                    <br>
                    <hr style="clear:both;">
                    <div class="table-responsive">
                        <table class="table  table-bordered">
                            <tbody>
                            </tbody>
                            <tfoot>
                            <tr>
                                    <h3 style="color: red;">您目前无权访问此页面！！！如需，烦请联系管理员解决</h3>
                                    <h3 style="color: red;">异常信息：${requestScope.errorMsg}</h3>
                                    <h3 style="color: red">资源：${requestScope.resource}</h3>
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
    /////////////////////////////////////////////////////////////////////////
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
</script>
<script type="text/javascript" src="${PATH }/static/include/role_js.js"></script>
</body>
</html>
