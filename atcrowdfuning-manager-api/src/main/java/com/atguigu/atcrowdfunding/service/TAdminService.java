package com.atguigu.atcrowdfunding.service;

import com.atguigu.atcrowdfuning.bean.TAdmin;

import java.util.ArrayList;
import java.util.List;

/**
 * @author erdong
 * @create 2019-09-10 8:22
 */
public interface TAdminService {

    TAdmin doLogin(String loginacct, String userpswd);

    List<TAdmin> listAdmins(String condition);

    void deleteAdmin(Integer id);

    void saveAdmin(TAdmin admin);

    TAdmin getAdminById(Integer id);

    void updateAdminById(TAdmin admin);

    void deleteAdmins(ArrayList<Integer> list);

    List<Integer> listAdminRoleIds(Integer id);

    void assignRoleToAdmin(Integer adminId, List<Integer> roleId);

    void unAssignRoleToAdmin(Integer adminId, List<Integer> roleId);
}
