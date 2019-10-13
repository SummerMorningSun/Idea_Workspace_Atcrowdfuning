package com.atguigu.springboot.mapper;

import com.atguigu.springboot.bean.TAdmin;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

// @Mapper  // 组件注解
public interface TAdminMapper {

    List<TAdmin> selectAdmins();

}