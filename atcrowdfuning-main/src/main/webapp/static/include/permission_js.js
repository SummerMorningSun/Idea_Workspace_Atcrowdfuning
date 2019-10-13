/**
 * 
 */
$(".tree a:contains('许可维护')").css("color" , "red");
//页面中的子菜单的显示隐藏本质是通过 css样式：display控制的，只要让用户维护所在的ul显示
$(".tree a:contains('许可维护')").parents("ul").show();
//为了避免点击父菜单 控制显示隐藏效果出错，自动展开 子菜单的同时需要移除 li的 treeclosed class属性值
$(".tree a:contains('许可维护')").parents("ul").parent("li").toggleClass("tree-closed");