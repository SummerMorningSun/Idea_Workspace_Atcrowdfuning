package com.atguigu.springboot.service.impl;

import com.atguigu.springboot.bean.TAdmin;
import com.atguigu.springboot.mapper.TAdminMapper;
import com.atguigu.springboot.service.TAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author erdong
 * @create 2019-09-21 10:00
 */
@Transactional  // 当前业务类的所有方法都当做是事务处理
@Service
public class TAdminServiceImpl implements TAdminService {

    @Autowired
    TAdminMapper adminMapper;

    @Override
    public List<TAdmin> listAdmins() {
        return adminMapper.selectAdmins();
    }

    @Override
    public TAdmin selectAdminById(Integer id) {
        return adminMapper.selectAdminById(id);
    }

    @Override
    public Boolean saveAdminByInfo(TAdmin Info) {
        return saveAdminByInfo(Info);
    }

    @Override
    public Boolean deleteAdminById(Integer id1, Integer id2) {
        Integer adminById1 = adminMapper.deleteAdminById1(id1);
        Integer adminById2 = adminMapper.deleteAdminById2(id2);
        if ((adminById1 + adminById2) != 2) {
            return false;
        }
        return true;
    }
}
