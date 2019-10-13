package com.atguigu.springboot.mapper;

import com.atguigu.springboot.bean.TAdmin;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

// @Mapper  // 组件注解
public interface TAdminMapper {

    // 了解一下，这种方法需要把配置的扫描mapper的配置给干掉，然后去跑到启动函数类上面添加扫描mapper接口的注释@MapperScan
    // 然后在所配置的扫描的接口中每个方法上面配上对应的sql功能即可
    // 此类方法仅供了解一下，
    @Select("select * from t_admin")
    List<TAdmin> selectAdmins();

    // 根据id查询用户信息
    @Select("select * from t_admin where id = #{id}")
    TAdmin selectAdminById(Integer id);

    // 新增管理
    @Insert("insert into t_admin(loginacct,username,userpswd,email) values (#{loginacct},#{username},#{userpswd},#{email})")
    Boolean saveAdminByInfo(TAdmin Info);

    @Delete("delete from t_admin where id = #{id}")
    Integer deleteAdminById1(Integer id1);

    @Delete("delete from t_admin where id = #{id}")
    Integer deleteAdminById2(Integer id1);

}