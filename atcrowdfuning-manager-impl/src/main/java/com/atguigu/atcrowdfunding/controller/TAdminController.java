package com.atguigu.atcrowdfunding.controller;

import com.atguigu.atcrowdfunding.consts.AppConsts;
import com.atguigu.atcrowdfunding.service.TAdminService;
import com.atguigu.atcrowdfunding.service.TMenuService;
import com.atguigu.atcrowdfunding.service.TRoleService;
import com.atguigu.atcrowdfunding.service.impl.AppUserDetailsServiceImpl;
import com.atguigu.atcrowdfuning.bean.TAdmin;
import com.atguigu.atcrowdfuning.bean.TMenu;
import com.atguigu.atcrowdfuning.bean.TRole;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @author erdong
 * @create 2019-09-09 23:21
 */
@Controller
public class TAdminController {

    @Autowired
    TAdminService adminService;

    @Autowired
    TMenuService menuService;

    @Autowired
    TRoleService roleService;

    @Autowired
    AppUserDetailsServiceImpl userDetailsService;

    Logger logger = LoggerFactory.getLogger(getClass());

    //处理异步删除角色的请求
    @ResponseBody
    @RequestMapping("/admin/unAssignRole")
    public String unAssignRole(Integer adminId, @RequestParam("roleId") List<Integer> roleId) {//如果使用集合类型入参，必须使用@ReqeustParam绑定参数
        //调用业务层将角色id和adminid绑定存到 t_admin_role表中
        adminService.unAssignRoleToAdmin(adminId, roleId);
        return "ok";
    }

    //处理异步分配角色的请求
    @ResponseBody
    @RequestMapping("/admin/assignRole")
    public String assignRole(Integer adminId, @RequestParam("roleId") List<Integer> roleId) {//如果使用集合类型入参，必须使用@ReqeustParam绑定参数
        //调用业务层将角色id和adminid绑定存到 t_admin_role表中
        adminService.assignRoleToAdmin(adminId, roleId);
        return "ok";
    }

    //跳转到分配角色的页面
    @RequestMapping("/admin/assignRole.html")
    public String toAssignRolePage(Model model, Integer id) {
        //准备点击分配角色按钮所在行的管理员  已分配和未分配角色集合
        //1、查询所有角色集合
        List<TRole> roles = roleService.listRoles(null);
        //2、查询id对应的管理员的已分配的角色id集合
        List<Integer> roleIds = adminService.listAdminRoleIds(id);
        //3、将所有角色的集合分成已分配和未分配的角色集合
        List<TRole> assignRoles = new ArrayList<TRole>();
        List<TRole> unAssignRoles = new ArrayList<TRole>();
        for (TRole role : roles) {
            //如果roleIds中包含当前role对象的id，当前角色就是已分配的角色
            if (roleIds.contains(role.getId())) {
                assignRoles.add(role);
            } else {
                unAssignRoles.add(role);
            }

        }
        //将角色集合共享到域中
        model.addAttribute("assignRoles", assignRoles);
        model.addAttribute("unAssignRoles", unAssignRoles);
        //转发到页面中显示角色集合
        return "admin/assignRole";

    }


    //处理批量删除请求的方法
    @RequestMapping("/admin/deleteAdmins")
    public String deleteAdmins(@RequestHeader("referer") String referer, String ids) {
        //解析参数：转为id的集合   ids=1,2,3,4   @RequestParam("ids") List<Integer> ids
        String[] idsArr = ids.split(",");
        //将字符串类型的数组转为Integer类型的List集合
        ArrayList<Integer> list = new ArrayList<Integer>();
        if (idsArr != null) {
            for (String idStr : idsArr) {
                int id = Integer.parseInt(idStr);
                list.add(id);
            }
        }
        //调用业务层处理批量删除的业务
        adminService.deleteAdmins(list);
        //跳转到删除之前的页面
        return "redirect:" + referer;
    }

    // 处理修改请求的方法
    @PostMapping("/admin/updateAdmin")
    public String updateAdmin(HttpSession session, TAdmin admin, Model model) {
        // 调用业务层修改
        try {
            adminService.updateAdminById(admin);
        } catch (Exception e) {
            // 如果修改失败，转发到修改页面，继续修改并提示
            model.addAttribute("errorMsg", e.getMessage());
            return "admin/edit";
        }
        // 修改后跳转回修改之后的页面(用户点击修改的当前页面)
        String ref = (String) session.getAttribute("ref");
        session.removeAttribute("ref"); // 关闭资源
        return "redirect:" + ref;
    }

    // 跳转到修改页面
    @RequestMapping("/admin/edit.html")
    public String toEditPage(HttpSession session, Integer id, HttpServletRequest request, @RequestHeader("referer") String referer) {
        // 获取要修改的管理员的信息
        TAdmin admin = adminService.getAdminById(id);
        // 将数据存到域中
        request.setAttribute("editAdmin", admin);
        // 转发到页面中显示要修改的管理员信息
        session.setAttribute("ref", referer);
        return "admin/edit";
    }


    // 跳转到添加管理员页面的方法
    @RequestMapping("/admin/add.html")
    public String toAddAdminPage() {
        return "admin/add";
    }

    @PreAuthorize("hasAnyRole('SE - 软件工程师','SA - 软件架构师')")
    @PostMapping("/admin/addAdmin")
    public String AddAdmin(Model model, HttpSession session, TAdmin admin) { // POJO 入参：请求参数的name属性值必须和bean的属性名一样
        // 调用业务逻辑层
        try {
            adminService.saveAdmin(admin);
        } catch (Exception e) {
            // 如果添加失败，转发到添加页面，继续添加并提示
            model.addAttribute("errorMsg", e.getMessage());
            return "admin/add";
        }
        // 跳转到管理员列表的最后一页？ pageNum=最大值
        // 获取分页数据的总页码
        Integer pages = (Integer) session.getAttribute(AppConsts.PAGES_COUNT);

        return "redirect:/admin/index.html?pageNum=" + (pages + 1);
    }

    // 删除指定id的管理员
    @PreAuthorize("hasAnyRole('SE - 软件工程师','SA - 软件架构师')")
    @RequestMapping("/admin/deleteAdmin")
    public String deleteAdmin(Integer id, @RequestHeader("referer") String referer) {
        //调用业务层
        adminService.deleteAdmin(id);
        // 删除成功跳转到响应页面
        return "redirect:" + referer;
    }

    // 跳转到用户维护页面
    @RequestMapping("/admin/index.html")
    public String toUserPage(HttpSession session, @RequestParam(value = "condition", defaultValue = "", required = false) String condition,
                             @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum, Model model) {
        // 开启分页
        PageHelper.startPage(pageNum, AppConsts.PAGE_SIZE); // 参数1:查询的分页的页码，参数2：每页显示的记录和条数
        // 查询管理员集合
        List<TAdmin> admins = adminService.listAdmins(condition);
        // 通过PageHelper去解析查询的结果获取更加详细的分页信息(类似web项目中的Page):包括页码，总页码，总记录条数，分页数据的集合
        PageInfo<TAdmin> pageInfo = new PageInfo<>(admins, AppConsts.NAV_SIZE);// 参数1分页数据集合，参数2分页页面需要显示的页码的个数
        // 将总页码存到session域中
        session.setAttribute(AppConsts.PAGES_COUNT, pageInfo.getPages());

        // 存到request域中共享
        model.addAttribute("pageInfo", pageInfo);
        // 转发到user页面获取遍历显示
        return "admin/user";
    }

    // 注销功能
/*
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        System.out.println("注销清除来过，，，，，，，，，，，，，，，，，，");
        return "index"; // 重定向到主页
    }
*/


    // 处理登录请求方法
/*    @PostMapping("/doLogin")
    public String doLogin(String loginacct, String userpswd, HttpServletRequest request, HttpSession session) {
        // 查询登录用户
        TAdmin tAdmin = adminService.doLogin(loginacct, userpswd);
        System.out.println("查询的登录账号信息：" + tAdmin);

        // 登录失败
        if (tAdmin == null) {
            String errorMsg = "账号或密码错误";
            request.setAttribute("errorMsg", errorMsg);
            return "login";
        }
        // 登录成功
        session.setAttribute("admin", tAdmin);
        return "redirect:/main.html";
    }*/

    @RequestMapping("/main.html")
    public String toMainPage(HttpSession session,HttpServletRequest request) {
        // 后台首页显示菜单集合
        List<TMenu> parentMenu = menuService.listMenus();
        logger.info("查询到的菜单集合：{}", parentMenu);
        // 准备菜单集合设置到session域中
        session.setAttribute("parents", parentMenu);

        return "admin/main";
    }

}
