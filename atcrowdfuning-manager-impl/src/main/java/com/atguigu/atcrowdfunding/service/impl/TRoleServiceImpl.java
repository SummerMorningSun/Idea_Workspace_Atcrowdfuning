package com.atguigu.atcrowdfunding.service.impl;

import com.atguigu.atcrowdfunding.service.TRoleService;
import com.atguigu.atcrowdfunding.utils.StringUtil;
import com.atguigu.atcrowdfuning.bean.TRole;
import com.atguigu.atcrowdfuning.bean.TRoleExample;
import com.atguigu.atcrowdfuning.bean.TRolePermissionExample;
import com.atguigu.atcrowdfuning.mapper.TRoleMapper;
import com.atguigu.atcrowdfuning.mapper.TRolePermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author erdong
 * @create 2019-09-16 8:32
 */
@Service
public class TRoleServiceImpl implements TRoleService {
    @Autowired
    TRoleMapper roleMapper;
    @Autowired
    TRolePermissionMapper rolePermissionMapper;

    @Override
    public List<TRole> listRoles(String condition) {
        TRoleExample e = null;
        if (!StringUtil.isEmpty(condition)) {
            e = new TRoleExample();
            e.createCriteria().andNameLike("%" + condition + "%"); // 模糊查询
        }
        return roleMapper.selectByExample(e);
    }

    @Override
    public void deleteRole(Integer id) {
        roleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void saveRole(TRole role) {
        roleMapper.insertSelective(role);
    }

    @Override
    public TRole getRoleById(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateRole(TRole role) {
        roleMapper.updateByPrimaryKeySelective(role);
    }

    @Override
    public void deleteRolePermissions(Integer id) {
        TRolePermissionExample example = new TRolePermissionExample();
        example.createCriteria().andRoleidEqualTo(id);
        rolePermissionMapper.deleteByExample(example);
    }

    @Override
    public void updateRolePermissions(Integer id, List<Integer> pids) {
        rolePermissionMapper.insertRolePermissionIds(id, pids);
    }

    @Override
    public List<Integer> getRolePermissionIds(Integer id) {
        List<Integer> permissionIds = rolePermissionMapper.selectPermissionIdsByRoleId(id);
        return permissionIds;
    }
}

