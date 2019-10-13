<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

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
		cursor:pointer;
	}
	table tbody tr:nth-child(odd){background:#F4F4F4;}
	table tbody td:nth-child(even){color:#C00;}
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
      <input name="condition" class="form-control has-success" type="text" placeholder="请输入查询条件">
    </div>
  </div>
  <button type="button" id="queryRolesBtn" class="btn btn-warning"><i class="glyphicon glyphicon-search"></i> 查询</button>
</form>
<button type="button" class="btn btn-danger" style="float:right;margin-left:10px;"><i class=" glyphicon glyphicon-remove"></i> 删除</button>
<button type="button" id="saveRoleModelBtn" class="btn btn-primary" style="float:right;"><i class="glyphicon glyphicon-plus"></i> 新增</button>
<br>
 <hr style="clear:both;">
          <div class="table-responsive">
            <table class="table  table-bordered">
              <thead>
                <tr >
                  <th width="30">#</th>
				  <th width="30"><input type="checkbox"></th>
                  <th>名称</th>
                  <th width="100">操作</th>
                </tr>
              </thead>
              <tbody>
                
              </tbody>
			  <tfoot>
			     <tr >
				     <td colspan="6" align="center">
						<ul class="pagination">
								
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
<!-- 拷贝bootstrap的模态框： 可以弹出一个弹窗显示或收集数据，背景页面不会刷新 -->
<!-- 添加角色的模态框 -->
<div class="modal fade" id="saveRoleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">添加角色</h4>
      </div>
      <div class="modal-body">
        <form>
          <div class="form-group">
            <label for="recipient-name" class="control-label">角色名:</label>
            <input type="hidden" class="form-control" name="pages" id="recipient-name">
            <input type="text" class="form-control" name="name" id="recipient-name">
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" class="btn btn-primary">提交</button>
      </div>
    </div>
  </div>
</div>
<!-- 修改角色的模态框 -->
<div class="modal fade" id="updateRoleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">修改角色</h4>
      </div>
      <div class="modal-body">
        <form>
          <div class="form-group">
            <label for="recipient-name" class="control-label">角色名:</label>
            <input type="text" class="form-control" name="name" id="recipient-name">
            <input type="hidden" class="form-control" name="id" id="recipient-name">
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" id="updateRoleBtn" class="btn btn-primary">修改</button>
      </div>
    </div>
  </div>
</div>
<!-- 给角色分配权限的模态框 -->
<div class="modal fade" id="assignPermissionsToRoleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="exampleModalLabel">分配许可</h4>
      </div>
      <div class="modal-body">
      <input type="hidden"  name="roleId" />
        <ul id="permissionsTree" class="ztree"></ul>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
        <button type="button" id="assignPermissionBtn" class="btn btn-primary">修改</button>
      </div>
    </div>
  </div>
</div>




   <%@ include file="/WEB-INF/include/base_js.jsp" %>
        <script type="text/javascript">
        	//给分配权限的 提交按钮绑定 单击事件
        	$("#assignPermissionsToRoleModal #assignPermissionBtn").click(function(){
        		//1、获取roleid
        		var roleId = $("#assignPermissionsToRoleModal input[name='roleId']").val();
        		//2、获取选中的所有的权限id集合
        		var treeObj = $.fn.zTree.getZTreeObj("permissionsTree");//获取ztree树 对象
        		var checkedNodes = treeObj.getCheckedNodes(true);//获取ztree树的被选中的复选框
        		//console.log(checkedNodes);
        		//3、遍历被选中的节点，拼接节点id参数
        		var pids = "";
        		$.each(checkedNodes , function(){
        			pids+=this.id+",";
        		})
        		pids = pids.substring(0 , pids.length-1);
        		
        		//4、发送ajax请求给服务器  修改权限[先删除角色的所有权限 ， 再给角色添加修改后的权限列表  t_role_permission]
        		$.ajax({
        			type:"POST",
        			url:"${PATH}/role/updatePermissions",
        			data:{"id":roleId , "pids": pids},
        			success: function(result){
        				if("ok"==result){
        					//关闭模态框
        					$("#assignPermissionsToRoleModal").modal("toggle");
        				}
        			}
        		});
        	});
        
        
        
        	//给新增按钮绑定单击事件，点击弹出收集添加角色信息模态框
        	$("#saveRoleModelBtn").click(function(){
        		$("#saveRoleModal").modal("toggle");
        	});
        	//给模态框的提交按钮绑定单击事件，点击收集角色信息异步提交给服务器，如果添加成功，刷新页面角色列表
        	$("#saveRoleModal .modal-footer button:contains('提交')").click(function(){
        		$.ajax({
        			type:"POST",
        			url:"${PATH}/role/saveRole",
        			data:{"name": $("#saveRoleModal input[name='name']").val()},
        			success:function(result){
        				if("ok"==result){
        					//获取页面初始化时 存到表单项中的总页码
        					var pages = $("#saveRoleModal input[name='pages']").val();
        					//异步请求最后一页的数据显示到页面中
        					initRolesTable(pages+1);
        					//隐藏模态框
        					$("#saveRoleModal").modal("hide");
        				}
        			}
        		});
        	});
        	//点击每一行role的修改按钮时，弹出修改的模态框
        	$("table tbody").delegate(".updateRoleBtn" , "click" , function(){
        		//获取点击按钮所在行角色的id   this代表被点击的按钮
        		var roleId = $(this).attr("rid");
        		//请求服务器获取点击按钮所在行的角色信息回显到修改的模态框中
        		$.post("${PATH}/role/getRole" ,{id:roleId} , function(role){
        			//alert(JSON.stringify(role));//json对象转json字符串
        			$("#updateRoleModal input[name='name']").val(role.name);
        			//修改时确定唯一记录的id也需要设置到表单中
        			$("#updateRoleModal input[name='id']").val(role.id);
	        		//弹出修改的模态框
	        		$("#updateRoleModal").modal("show");
        		});
        	})
        	//给修改的模态框的修改按钮绑定单击事件：点击时 提交表单数据给服务器修改角色，修改成功后关闭模态框并刷新当前页面的角色列表数据
        	$("#updateRoleModal #updateRoleBtn").click(function(){
        		$.post("${PATH}/role/updateRole" , $("#updateRoleModal form").serialize() ,function(result){
        			if("ok"== result){
        				$("#updateRoleModal").modal("hide");
        				//获取被点击标签所在的页码
		            	var pageNum = $("table tfoot .active").prop("id");
		            	//分页时有可能是带条件的查询
		            	//获取条件
		            	var condition = $("input[name='condition']").val();
	            		initRolesTable(pageNum , condition);
        			}
        		})
        	});
        	
        	
        	/////////////////////////////////////////////////////////////////////////
            $(function () {
			    $(".list-group-item").click(function(){
				    if ( $(this).find("ul") ) {
						$(this).toggleClass("tree-closed");
						if ( $(this).hasClass("tree-closed") ) {
							$("ul", this).hide("fast");
						} else {
							$("ul", this).show("fast");
						}
					}
				});
            });
            
            $("tbody .btn-success").click(function(){
                window.location.href = "assignPermission.html";
            });
            //点击删除按钮 删除所在行角色的点击事件
            function deleteRole(roleId){
            	//this代表window对象
            	//alert(roleId);
            	//异步提交删除请求
            	$.ajax({
            		type:"POST",
            		url:"${PATH}/role/deleteRole",
            		data:{"id":roleId},
            		success:function(result){
            			//如果响应删除成功，那么删除页面的role行
            			if("ok"==result){
	            			$("#r_"+roleId).remove();
			            	if($("table tbody .btn-success").length==0){
				            	//如果当前页面中一个role都没有了刷新表格数据(重新调用initRolesTable  并传入当前页页码)
				            	//获取被点击标签所在的页码
				            	var pageNum = $("table tfoot .active").prop("id");
				            	//分页时有可能是带条件的查询
				            	//获取条件
				            	var condition = $("input[name='condition']").val();
			            		initRolesTable(pageNum , condition);
			            	}
            			}
		            	
            		}
            	});
            }
            //给角色分配权限的按钮的单击事件
            function assignPermissionToRole(roleId){
            	//1、异步请求根据roleId查询 角色已经拥有的权限id列表
            	$.post("${PATH}/role/getRolePermissionIds" , {"id":roleId} , function(permissionIds){
            		//alert(JSON.stringify(permissionIds));
	            	//2、查询所有的权限列表
	            	$.get("${PATH}/permission/getPermissions" , function(permissions){
	            		//ztree在加载数据时，如果正在遍历的节点有checked属性=true，则会自动设置选中
	            		$.each(permissions , function(){
		            		if(permissionIds.indexOf(this.id)>-1){
								//集合中包含元素
								this.checked = true;
							} 
	            		});
	            		// var jsObj = {id:11 , name:"xxxx"}  ;  jsObj.password="xxxx";
	            		
	            		
	            		//console.log(permissions);
		            	//3、将权限列表以带复选框的树状形式显示到模态框中(已拥有的角色需要设置默认选中)
		            	//3.1 引入ztree相关的 js和css文件
		            	//3.2 页面中准备ul容器
		            	//3.3 设置setting 调用ztree的init方法将permissions解析显示到模态框的树的容器中
		            	function addDiyDom(treeId, treeNode) {
		            		//console.log(treeNode);
							$("#"+treeNode.tId+"_ico").removeClass();
							$("#"+treeNode.tId+"_span").before("<span class='"+treeNode.icon+"'></span>");
							//获取当前节点的复选框，如果当前节点的id 在角色拥有的权限id集合中存在，那么就设置默认选中
							
							
						};
		            	var setting = {
	            			view: {
	            				addDiyDom: addDiyDom
	            			},
	            			check:{
	            				enable:true
	            			},
		            		data:{
		            			key:{
		            				name:"title"
		            			},
		            			simpleData: {
		            				enable: true,
		            				pIdKey: "pid"
		            			}
		            		}
		            	};
		            	var $ztreeNode = $.fn.zTree.init($("#permissionsTree"), setting, permissions);
		            	$ztreeNode.expandAll(true);
		            	//将角色id存到 模态框中
		            	$("#assignPermissionsToRoleModal input[name='roleId']").val(roleId);
		            	//4、显示模态框
	            		$("#assignPermissionsToRoleModal").modal("toggle");
	            	});
            	});
            }
            
            
            
            //解析role列表的方法
            function initTableBody(roles){
         		//1、获取存放role列表的表格的tbody
           		var $tbody = $("table tbody");
         		$tbody.empty();
            	//console.log(roles);
            	//解析roles列表，一个role对应一行，role的一个属性对应一个td
          		//2、遍历json数组，将每个json对象数据显示到tr中，将tr追加给tbody
            	$.each(roles , function(index){
	          		//this代表正在遍历的json对象：  {id:1 , name:"xxxx"}
            		/*             			js中如果内容一行写不下，需要折行，使用\  代表当前行内容仍未结束
            		*/
            		$("<tr id='r_"+this.id+"'></tr>").append("<td>"+(index+1)+"</td>")
            					.append('<td><input type="checkbox"></td>')
            					.append('<td>'+this.name+'</td>')
            					.append(' <td>\
          						      <button type="button"  onclick="assignPermissionToRole('+this.id+')"  class="btn btn-success btn-xs"><i class=" glyphicon glyphicon-check"></i></button>\
        						      <button type="button" rid="'+this.id+'" class="btn btn-primary btn-xs updateRoleBtn"><i class=" glyphicon glyphicon-pencil"></i></button>\
        							  <button type="button"   onclick="deleteRole('+this.id+')" class="btn btn-danger btn-xs"><i class=" glyphicon glyphicon-remove"></i></button>\
        						  </td>')
        						  .appendTo($tbody);//将创建完毕的tr追加到tbody内部
            	})
            }
            //解析分页详情显示分页导航栏
            function initTableFoot(pageInfo){
	        	var $ul = $("table tfoot .pagination");//显示分页导航栏的ul
	        	$ul.empty();
	        	//上一页
	        	if(pageInfo.hasPreviousPage){
	        		//有上一页
	        		$('<li><a class="listRolesA" pageNum="'+(pageInfo.pageNum-1)+'" href="${PATH}/role/listRoles?pageNum='+(pageInfo.pageNum-1)+'">上一页</a></li>').appendTo($ul);
	        	}else{
	        		//没有上一页
	        		$('<li class="disabled"><a href="javascript:void(0);">上一页</a></li>').appendTo($ul);
	        	}
	        	//页码 [1,2,3] 
	        	$.each(pageInfo.navigatepageNums , function(){
	        		//this 代表正在遍历的元素,页码数字
	        		if(this==pageInfo.pageNum){
	        			$('<li class="active"  id="'+this+'"><a href="javascript:void(0);">'+ this +' <span class="sr-only">(current)</span></a></li>').appendTo($ul);
	        		}else{
	        			$('<li><a class="listRolesA" pageNum="'+this+'"  href="${PATH}/role/listRoles?pageNum='+this+'">'+this+'</a></li>').appendTo($ul);
	        		}
	        		
	        	});
	        	
	        	//下一页
	        	if(pageInfo.hasNextPage){
	        		//有上一页
	        		$('<li><a class="listRolesA" pageNum="'+(pageInfo.pageNum+1)+'">下一页</a></li>').appendTo($ul);
	        	}else{
	        		//没有上一页
	        		$('<li class="disabled"><a href="javascript:void(0);">下一页</a></li>').appendTo($ul);
	        	}
            }
            
           	//提取异步查询分页信息并解析显示到页面中的代码
           	function initRolesTable(pageNum , condition){
	           	 $.get("${PATH}/role/listRoles" , {"pageNum": pageNum , "condition":condition} , function(pageInfo){
	             	console.log(pageInfo);
	             	//将总页码存到页面指定的标签内，当点新增按钮时可以获取总页码使用
	             	$("#saveRoleModal input[name='pages']").val(pageInfo.pages);
	 				//提取解析role列表的代码    
	 				initTableBody(pageInfo.list);
	             	//生成分页导航栏 追加给 表格的tfoot中的ul
	             	//提取分页导航栏解析的代码
	 				initTableFoot(pageInfo);
	             });
            }
            //当页面打开后，立即发送ajax请求给服务器获取role列表，然后再通过json解析 dom操作将role列表显示到表格中
            initRolesTable(1);
            //???????????? 同步操作中不能给异步操作解析后的标签绑定事件,可以通过事件委派解决(祖先元素.delegate("需要动态绑定事件的标签选择器" , "事件类型" , function(){事件处理函数})   )
            //给所有的分页导航栏a标签绑定单击事件，点击时调用initRolesTable方法查询分页数据并解析显示到表格中
            $("table tfoot").delegate(".listRolesA" , "click" , function(){
            	//获取被点击标签所在的页码
            	var pageNum = $(this).attr("pageNum");
            	//分页时有可能是带条件的查询
            	//获取条件
            	var condition = $("input[name='condition']").val();
            	//查询页码所在的分页信息并显示到表格中
            	initRolesTable(pageNum,condition);
            	return false;
            })
            
            //给带条件的查询按钮绑定单击事件
            $("#queryRolesBtn").click(function(){
            	//获取条件
            	var condition = $("input[name='condition']").val();
            	//调用提取的 异步查询role列表并解析显示到表格中的方法
            	initRolesTable(1 , condition);
            });
            
        </script>
        <script type="text/javascript" src="${PATH }/static/include/role_js.js"></script>
  </body>
</html>
