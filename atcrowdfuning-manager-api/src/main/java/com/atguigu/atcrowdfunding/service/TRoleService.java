package com.atguigu.atcrowdfunding.service;

import com.atguigu.atcrowdfuning.bean.TRole;

import java.util.List;

/**
 * @author erdong
 * @create 2019-09-16 8:30
 */
public interface TRoleService {

    List<TRole> listRoles(String condition);

    void saveRole(TRole role);

    void deleteRole(Integer id);

    TRole getRoleById(Integer id);

    void updateRole(TRole role);

    void deleteRolePermissions(Integer id);

    void updateRolePermissions(Integer id, List<Integer> pids);

    List<Integer> getRolePermissionIds(Integer id);
}
