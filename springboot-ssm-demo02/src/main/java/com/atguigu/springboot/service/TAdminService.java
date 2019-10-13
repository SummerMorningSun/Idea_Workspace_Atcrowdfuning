package com.atguigu.springboot.service;

import com.atguigu.springboot.bean.TAdmin;

import java.util.List;

/**
 * @author erdong
 * @create 2019-09-10 8:22
 */
public interface TAdminService {

    List<TAdmin> listAdmins();

    TAdmin selectAdminById(Integer id);

    Boolean saveAdminByInfo(TAdmin Info);

    Boolean deleteAdminById(Integer id1, Integer id2);
}
