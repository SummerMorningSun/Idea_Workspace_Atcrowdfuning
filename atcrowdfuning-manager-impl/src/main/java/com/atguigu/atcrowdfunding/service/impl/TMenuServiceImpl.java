package com.atguigu.atcrowdfunding.service.impl;

import com.atguigu.atcrowdfunding.service.TMenuService;
import com.atguigu.atcrowdfuning.bean.TMenu;
import com.atguigu.atcrowdfuning.mapper.TMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author erdong
 * @create 2019-09-10 20:25
 */
@Service
public class TMenuServiceImpl implements TMenuService {

    @Autowired
    TMenuMapper menuMapper;

    @Override
    public List<TMenu> listMenus() {
        //查询所有的menu菜单集合
        List<TMenu> menus = menuMapper.selectByExample(null);
        //挑出父菜单集合
        //List<TMenu> pMenus = new ArrayList<TMenu>();
        Map<Integer, TMenu> pMenusMap = new HashMap<Integer, TMenu>();
        for (TMenu menu : menus) {
            if (menu.getPid() == 0) {
                //pMenus.add(menu);
                pMenusMap.put(menu.getId(), menu);//存父菜单 存id，
            }
        }
        //遍历将所有的子菜单添加到它的父菜单的children集合中
        for (TMenu menu : menus) {
            //使用正在遍历的菜单对象的父id和  父菜单集合中的菜单对象比较，如果一样，则是它的子菜单
            TMenu pMenu = pMenusMap.get(menu.getPid());
            if (pMenu != null) {
                pMenu.getChildren().add(menu);
            }
        }
        //返回封装完毕的父菜单集合
        return new ArrayList<TMenu>(pMenusMap.values());
    }

    @Override
    public void deleteMenu(Integer id) {
        menuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public void saveMenu(TMenu menu) {
        menuMapper.insertSelective(menu);
    }

    @Override
    public void updateMenu(TMenu menu) {
        menuMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public TMenu getMenu(Integer id) {
        return menuMapper.selectByPrimaryKey(id);
    }
}
