package com.atguigu.atcrowdfunding.service.impl;

import java.util.List;

import com.atguigu.atcrowdfuning.mapper.TPermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atguigu.atcrowdfuning.bean.TPermission;
import com.atguigu.atcrowdfunding.service.TPermissionService;

@Service
public class TPermissionServiceImpl implements TPermissionService {

    @Autowired
    TPermissionMapper permissionMapper;

    @Override
    public List<TPermission> listAllPermissions() {
        return permissionMapper.selectByExample(null);
    }

}
