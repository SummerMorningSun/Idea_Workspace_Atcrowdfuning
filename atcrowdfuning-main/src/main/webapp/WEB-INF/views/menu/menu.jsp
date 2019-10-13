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
    </style>
</head>

<body>

<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <div>
                <a class="navbar-brand" style="font-size: 32px;" href="#">众筹平台
                    - 许可维护</a>
            </div>
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
                    <i class="glyphicon glyphicon-th-list"></i> 权限菜单列表
                    <div style="float: right; cursor: pointer;" data-toggle="modal"
                         data-target="#myModal">
                        <i class="glyphicon glyphicon-question-sign"></i>
                    </div>
                </div>
                <div class="panel-body">
                    <ul id="treeDemo" class="ztree"></ul>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 添加菜单节点的模态框 -->
<div class="modal fade" id="saveMenuModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="exampleModalLabel">添加菜单</h4>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <input type="hidden" name="pid" class="form-control" id="recipient-name">
                        <label for="recipient-name" class="control-label">菜单名称:</label>
                        <input type="text" name="name" class="form-control" id="recipient-name">
                    </div>
                    <div class="form-group">
                        <label for="message-text" class="control-label">菜单图标:</label>
                        <input type="text" name="icon" class="form-control" id="recipient-name">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" id="saveMenuBtn" class="btn btn-primary">Send message</button>
            </div>
        </div>
    </div>
</div>
<!-- 修改菜单的模态框 -->
<div class="modal fade" id="updateMenuModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="exampleModalLabel">修改菜单</h4>
            </div>
            <div class="modal-body">
                <form>
                    <div class="form-group">
                        <input type="hidden" name="id" class="form-control" id="recipient-name">
                        <label for="recipient-name" class="control-label">菜单名称:</label>
                        <input type="text" name="name" class="form-control" id="recipient-name">
                    </div>
                    <div class="form-group">
                        <label for="message-text" class="control-label">菜单图标:</label>
                        <input type="text" name="icon" class="form-control" id="recipient-name">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button" id="updateMenuBtn" class="btn btn-primary">修改</button>
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

    //ztree的使用
    //1、引入jquery.js文件  ztree的style样式、js文件
    //2、在页面中准备ztree的容器[ul ]
    //3、在js代码中准备 ztree的setting配置、ztree需要的数据源(json字符串)

    //自定义ztree树节点的方法：当生成ztree树的每个节点时都会使用当前节点调用一次此方法
    function addDiyDom(treeId, treeNode) {//treeNode就代表调用方法的ztree的树节点对象
        console.log(treeNode);//treeNode就是根据数据源中的菜单节点生成的对象
        //获取当前节点生成的a标签：
        $("#" + treeNode.tId + "_a").attr("href", "javascript:void(0);");
        //移除显示当前节点图标的span标签的所有的class值
        $("#" + treeNode.tId + "_ico").removeClass();
        //$("#"+treeNode.tId+"_ico").addClass(treeNode.icon);
        //新创建span标签显示字体图片然后设置给当前的节点
        $("#" + treeNode.tId + "_span").before("<span class='" + treeNode.icon + "'></span")
    };

    //鼠标在节点上悬停时的操作
    function addHoverDom(treeId, treeNode) {
        var aObj = $("#" + treeNode.tId + "_a"); // tId = permissionTree_1, ==> $("#permissionTree_1_a")
        aObj.attr("href", "javascript:;");
        if (treeNode.editNameFlag || $("#btnGroup" + treeNode.tId).length > 0) return;
        var s = '<span menuid="' + treeNode.id + '" id="btnGroup' + treeNode.tId + '">';
        if (treeNode.level == 0) {//根节点  只能添加子节点
            s += '<a onclick="addMenu(' + treeNode.id + ')" class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="javascript:void(0);" >&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
        } else if (treeNode.level == 1) {//枝节点:枝节点如果有子节点可以修改和增加子节点 ， 如果枝节点没有子节点可以增删改
            s += '<a class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  href="javascript:void(0);" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
            if (treeNode.children.length == 0) {
                s += '<a class="btn btn-info dropdown-toggle btn-xs deleteMenuBtn" style="margin-left:10px;padding-top:0px;" href="javascript:void(0);" >&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
            }
            s += '<a onclick="addMenu(' + treeNode.id + ')" class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;" href="javascript:void(0);" >&nbsp;&nbsp;<i class="fa fa-fw fa-plus rbg "></i></a>';
        } else if (treeNode.level == 2) {//叶子节点
            s += '<a onclick="updateMenu(' + treeNode.id + ')" class="btn btn-info dropdown-toggle btn-xs" style="margin-left:10px;padding-top:0px;"  href="javascript:void(0);" title="修改权限信息">&nbsp;&nbsp;<i class="fa fa-fw fa-edit rbg "></i></a>';
            s += '<a class="btn btn-info dropdown-toggle btn-xs deleteMenuBtn" style="margin-left:10px;padding-top:0px;" href="javascript:void(0);">&nbsp;&nbsp;<i class="fa fa-fw fa-times rbg "></i></a>';
        }

        s += '</span>';
        aObj.after(s);

    };

    //修改菜单节点
    function updateMenu(id) {
        //1、根据菜单id查询菜单信息     严格的json语法：属性名必须使用双引号引起
        $.post("${PATH}/menu/getMenu", {"id": id}, function (menu) {
            //2、将菜单信息回显到模态框中
            $("#updateMenuModal input[name='name']").val(menu.name);
            $("#updateMenuModal input[name='icon']").val(menu.icon);
            $("#updateMenuModal input[name='id']").val(menu.id);
            //3、弹出模态框
            $("#updateMenuModal").modal("toggle");

        })

    }

    //给修改模态框修改按钮绑定修改单击事件
    $("#updateMenuModal #updateMenuBtn").click(function () {
        $.post("${PATH}/menu/updateMenu", $("#updateMenuModal form").serialize(), function (result) {
            if ("ok" == result) {
                $("#updateMenuModal").modal("toggle");
                //刷新ztree树
                initMenusTree();
            }
        });
    });

    //添加节点弹出模态框的方法
    function addMenu(pid) {
        //获取点击添加按钮所在行的菜单id，作为新增菜单的pid，然后设置到模态框
        $("#saveMenuModal input[name='pid']").val(pid);
        //弹出添加的收集菜单节点的模态框
        $("#saveMenuModal").modal("toggle");
    }

    //添加模态框确定按钮提交的方法
    $("#saveMenuModal #saveMenuBtn").click(function () {
        //提交数据给服务器保存
        $.post("${PATH}/menu/saveMenu", $("#saveMenuModal form").serialize(), function (result) {
            if ("ok" == result) {
                /*
                    响应数据给浏览器时，如果是ajax请求，需要判断本次请求是否成功
                        但是ajax响应的数据有可能是简单的字符串、还有可能是复杂的json、还有可能是xml文本
                        服务器可以创建一个响应类
                            AppResponse<T>:
                                    Integer code; 本次请求的响应状态码
                                    String message; 请求的响应信息
                                    T data; 本次请求希望响应给用户的数据
                                    当响应此对象给浏览器的ajax请求时，jackson会自动将其转为json字符串
                                        {code:xxx , message:xxxxx , data: xxx}
                */
                //关闭模态框
                $("#saveMenuModal").modal("toggle");
                //刷新ztree树
                initMenusTree();
            }
        });
    });

    //给节点树 生成的 删除按钮绑定单击事件:点击时删除按钮所在的节点
    $("#treeDemo").delegate(".deleteMenuBtn", "click", function () {
        //获取被点击按钮所在节点的菜单的id
        var menuid = $(this).parent().attr("menuid");
        //alert(menuid);
        //提交删除请求，删除成功后，刷新菜单树
        $.post("${PATH}/menu/deleteMenu", {id: menuid}, function (result) {
            if ("ok" == result) {
                //刷新菜单树
                initMenusTree();
            }
        });

    });


    //鼠标离开时的操作
    function removeHoverDom(treeId, treeNode) {
        $("#btnGroup" + treeNode.tId).remove();//移除addHoverDom生成的按钮组
    };
    var setting = {
        view: {
            addDiyDom: addDiyDom,
            addHoverDom: addHoverDom,
            removeHoverDom: removeHoverDom
        },
        data: {
            /* key:{
                url:"xx"
            }, */
            simpleData: {
                enable: true,
                pIdKey: "pid"
            }
        }

    };

    function initMenusTree() {
        //数据源希望从数据库中异步查询
        $.ajax({
            type: "GET",
            url: "${PATH}/menu/listMenus",
            dataType: "JSON",
            success: function (menus) {//menus就是服务器响应的菜单的json字符串，就是数据源
                if (menus.code == 403) {
                    $("#treeDemo").append("<h3 style='color: red'>" + menus.errorMsg + "</h3>" + "<h3 style='color: red'>" + '请求失败资源:' + menus.resource + "</h3>");
                    return;
                }
                //alert(JSON.stringify(menus));
                //获取到数据源，给数据源最外层的菜单添加父菜单：最外层的菜单有pid属性=0 ， 如果添加元素的id=0，那么就是最外层菜单的父菜单
                menus.push({
                    id: 0,
                    name: "系统权限菜单",
                    icon: "glyphicon glyphicon-flag"
                });
                console.log(menus);
                //4、调用ztree的init方法按照设置 将数据源解析显示到容器中
                var $treeNode = $.fn.zTree.init($("#treeDemo"),
                    setting, menus);
                $treeNode.expandAll(true);
            }
        });
    }

    //页面加载完成立即加载菜单树
    initMenusTree();
</script>
<script type="text/javascript" src="${PATH }/static/include/menu_js.js"></script>
</body>
</html>
