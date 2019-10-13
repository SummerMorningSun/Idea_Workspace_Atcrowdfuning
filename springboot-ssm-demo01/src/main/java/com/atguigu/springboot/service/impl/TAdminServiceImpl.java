package com.atguigu.springboot.service.impl;

import com.atguigu.springboot.bean.TAdmin;
import com.atguigu.springboot.mapper.TAdminMapper;
import com.atguigu.springboot.service.TAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author erdong
 * @create 2019-09-21 10:00
 */
@Service
public class TAdminServiceImpl implements TAdminService {

    @Autowired
    TAdminMapper adminMapper;

    @Override
    public List<TAdmin> listAdmins() {
        return adminMapper.selectAdmins();
    }
}
