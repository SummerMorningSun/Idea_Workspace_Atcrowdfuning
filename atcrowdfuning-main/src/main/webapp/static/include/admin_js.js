/**
 *
 */
//设置当前页面的"控制面板"高亮显示
$(".tree a:contains('用户维护')").css("color", "red");
//页面中的子菜单的显示隐藏本质是通过css样式：display控制的，只要让用户维护的ul显示即可
$(".tree a:contains('用户维护')").parents("ul").show();
// 为了避免父菜单 控制显示隐藏效果出错，自动展开了子菜单的同时需要移除li的tree-closed属性值
$(".tree a:contains('用户维护')").parents("ul").parent("li").toggleClass("tree-closed");