package com.atguigu.atcrowdfuning.mapper;

import com.atguigu.atcrowdfuning.bean.TAdminRole;
import com.atguigu.atcrowdfuning.bean.TAdminRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TAdminRoleMapper {
    long countByExample(TAdminRoleExample example);

    int deleteByExample(TAdminRoleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TAdminRole record);

    int insertSelective(TAdminRole record);

    List<TAdminRole> selectByExample(TAdminRoleExample example);

    TAdminRole selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TAdminRole record, @Param("example") TAdminRoleExample example);

    int updateByExample(@Param("record") TAdminRole record, @Param("example") TAdminRoleExample example);

    int updateByPrimaryKeySelective(TAdminRole record);

    int updateByPrimaryKey(TAdminRole record);

    //根据管理员id查询拥有的角色id集合
    List<Integer> selectRoleIdsByAdminId(Integer id);
    //保存管理员id和管理员角色id的方法
    void assignRoleIdsToAdmin(@Param("adminId")Integer adminId, @Param("roleIds")List<Integer> roleIds);
}