package com.atguigu.atcrowdfunding.controller;

import java.util.List;

import com.atguigu.atcrowdfunding.service.TRoleService;
import com.atguigu.atcrowdfuning.bean.TRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.atcrowdfunding.consts.AppConsts;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("/role")
public class TRoleController {

    @Autowired
    TRoleService roleService;

    //给角色分配权限的异步请求
    @ResponseBody
    @RequestMapping("/updatePermissions")
    public String updatePermissions(Integer id, @RequestParam("pids") List<Integer> pids) {
        roleService.deleteRolePermissions(id);//删除角色所有的权限
        roleService.updateRolePermissions(id, pids);//给角色添加修改后的权限
        return "ok";
    }

    //查询角色已拥有的权限id集合的异步请求
    @ResponseBody
    @RequestMapping("/getRolePermissionIds")
    public List<Integer> getRolePermissionIds(Integer id) {
        List<Integer> permissionIds = roleService.getRolePermissionIds(id);
        return permissionIds;
    }

    //处理修改角色的请求:异步请求
    @ResponseBody
    @RequestMapping("/updateRole")
    public String updateRole(TRole role) {
        roleService.updateRole(role);
        return "ok";
    }

    //根据id查询角色对象的方法:异步请求
    @ResponseBody
    @RequestMapping("/getRole")
    public TRole getRole(Integer id) {
        TRole role = roleService.getRoleById(id);
        return role;
    }

    //处理添加角色的请求
    @ResponseBody
    @RequestMapping("/saveRole")
    public String saveRole(TRole role) {
        roleService.saveRole(role);
        return "ok";
    }


    //处理删除指定角色的方法
    @ResponseBody
    @RequestMapping("/deleteRole")
    public String deleteRole(Integer id) {
        roleService.deleteRole(id);
        return "ok";
    }


    //跳转到role列表显示页面
    @RequestMapping("/index.html")
    public String toRolePage() {
        return "role/role";
    }

    //处理ajax异步查询角色集合的方法
    @ResponseBody  //springmvc的 HttpMessageConverter 自动查找jackson处理响应结果
    @RequestMapping("/listRoles")
    public PageInfo<TRole> listRoles(@RequestParam(value = "condition", defaultValue = "", required = false) String condition,
                                     @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum) {
        //开启分页
        PageHelper.startPage(pageNum, AppConsts.PAGE_SIZE);
        List<TRole> roles = roleService.listRoles(condition);
        //获取详细的分页信息
        PageInfo<TRole> pageInfo = new PageInfo<TRole>(roles, AppConsts.NAV_SIZE);
        //将roles集合响应给ajax的请求：转为json字符串传递
        return pageInfo;
    }

}
