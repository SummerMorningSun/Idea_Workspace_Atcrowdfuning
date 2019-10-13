package com.atguigu.atcrowdfunding.service.impl;

import com.atguigu.atcrowdfunding.exception.TAdminAcctException;
import com.atguigu.atcrowdfunding.service.TAdminService;
import com.atguigu.atcrowdfunding.utils.AppDateUtils;
import com.atguigu.atcrowdfunding.utils.StringUtil;
import com.atguigu.atcrowdfuning.bean.TAdmin;
import com.atguigu.atcrowdfuning.bean.TAdminExample;
import com.atguigu.atcrowdfuning.bean.TAdminRoleExample;
import com.atguigu.atcrowdfuning.mapper.TAdminMapper;
import com.atguigu.atcrowdfuning.mapper.TAdminRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author erdong
 * @create 2019-09-10 8:28
 */
@Service
public class TAdminServiceImpl implements TAdminService {

    @Autowired
    TAdminMapper adminMapper;

    @Autowired
    TAdminRoleMapper adminRoleMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * 登录
     *
     * @param loginacct
     * @param userpswd
     * @return
     */
    @Override
    public TAdmin doLogin(String loginacct, String userpswd) {
        userpswd = passwordEncoder.encode(userpswd);
        TAdminExample example = new TAdminExample();
        example.createCriteria().andLoginacctEqualTo(loginacct).andUserpswdEqualTo(userpswd);
        List<TAdmin> tAdmins = adminMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(tAdmins) || tAdmins.size() != 1) {
            return null;
        }
        return tAdmins.get(0);
    }

    /**
     * 查询所有用户
     *
     * @param condition
     * @return
     */
    @Override
    public List<TAdmin> listAdmins(String condition) {
        TAdminExample e = null;

        // 判断是否传入了查询条件
        if (!StringUtil.isEmpty(condition)) {
            e = new TAdminExample();
            // 查询账号，姓名或邮箱中包含condition的所有的管理员集合
            e.createCriteria().andLoginacctLike("%" + condition + "%");
            // 用户名
            TAdminExample.Criteria criteria2 = e.createCriteria().andUsernameLike("%" + condition + "%");
            // 邮箱
            TAdminExample.Criteria criteria3 = e.createCriteria().andEmailLike("%" + condition + "%");
            e.or(criteria2);
            e.or(criteria3);
        }
        //如果没有传入条件，还是查询所有管理员
        return adminMapper.selectByExample(e);
    }

    /**
     * 根据id删除用户
     *
     * @param id
     */
    // 注意：@PreAuthorize是spring注解需要加到 @service标注的类
    @Override
    public void deleteAdmin(Integer id) {
        adminMapper.deleteByPrimaryKey(id);
    }

    /**
     * 添加用户信息
     *
     * @param admin
     */
    @Override
    public void saveAdmin(TAdmin admin) {
        checkAdmin(admin);
        adminMapper.insertSelective(admin);
    }

    @Override
    public TAdmin getAdminById(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateAdminById(TAdmin admin) {
        checkAdmin(admin);
        adminMapper.updateByPrimaryKeySelective(admin);// admin必须包含id属性值
    }

    @Override
    public void deleteAdmins(ArrayList<Integer> list) {
        TAdminExample example = new TAdminExample();
        example.createCriteria().andIdIn(list);
        adminMapper.deleteByExample(example);
    }

    @Override
    public List<Integer> listAdminRoleIds(Integer id) {
        List<Integer> ids = adminRoleMapper.selectRoleIdsByAdminId(id);
        return ids;
    }

    @Override
    public void assignRoleToAdmin(Integer adminId, List<Integer> roleId) {
        //insert into t_admin_role (adminid ,roleid) values(? , ?) , (? , ?);
        adminRoleMapper.assignRoleIdsToAdmin(adminId, roleId);
    }

    @Override
    public void unAssignRoleToAdmin(Integer adminId, List<Integer> roleId) {
        TAdminRoleExample example = new TAdminRoleExample();
        example.createCriteria().andAdminidEqualTo(adminId).andRoleidIn(roleId);
        //delete from t_admin_role where adminid = ? and roleid in (roleId);
        adminRoleMapper.deleteByExample(example);
    }

    // 检验用户信息是否已存在
    public void checkAdmin(TAdmin admin) {
        TAdminExample example = new TAdminExample();
        // 账号和邮箱必须唯一
        // 如果账号或者邮箱可以查询到管理员，证明账号和邮箱已经被占用，不让注册
        example.createCriteria().andLoginacctEqualTo(admin.getLoginacct());
        long l = adminMapper.countByExample(example);

        if (l > 0) {
            throw new TAdminAcctException("该账号已被占用");
        }
        example.clear();
        example.createCriteria().andEmailEqualTo(admin.getEmail());
        l = adminMapper.countByExample(example);
        if (l > 0) {
            throw new TAdminAcctException("该邮箱已被占用");
        }
        // 密码加密
        admin.setUserpswd(passwordEncoder.encode(admin.getUserpswd()));
        // 完善admin的其他属性值
        admin.setCreatetime(AppDateUtils.getFormatTime());
    }

}




















