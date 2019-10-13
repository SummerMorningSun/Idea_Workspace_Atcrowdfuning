package com.atguigu.atcrowdfunding.service.impl;

import com.atguigu.atcrowdfuning.bean.TAdmin;
import com.atguigu.atcrowdfuning.bean.TAdminExample;
import com.atguigu.atcrowdfuning.bean.TRole;
import com.atguigu.atcrowdfuning.mapper.TAdminMapper;
import com.atguigu.atcrowdfuning.mapper.TPermissionMapper;
import com.atguigu.atcrowdfuning.mapper.TRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author erdong
 * @create 2019-09-17 22:40
 */
@Service
public class AppUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    TAdminMapper adminMapper;
    @Autowired
    TRoleMapper roleMapper;
    @Autowired
    TPermissionMapper permissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // mapper 查询管理员信息
        TAdminExample example = new TAdminExample();
        example.createCriteria().andLoginacctEqualTo(username);
        List<TAdmin> list = adminMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list) || list.size() > 1) {
            return null;
        }
        // 数据库中保存的管理员信息对象
        TAdmin admin = list.get(0);
        // mapper 查询角色和权限集合
        // 查询角色name+id : 逆向mapper只能完成单表的增删改查
        List<TRole> roles = roleMapper.selectRoleByAdminId(admin.getId());
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        // 遍历集合将角色名添加到权限集合中
        for (TRole role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
            //根据角色id查询权限name
            List<String> permissionNames = permissionMapper.selectPermissionNamesByRoleId(role.getId());
            for (String name : permissionNames) {
                authorities.add(new SimpleGrantedAuthority(name));
            }
        }
        System.out.println(username + "权限列表" + authorities);
        return new User(admin.getLoginacct(), admin.getUserpswd(), authorities);
    }
}
