package com.atguigu.atcrowdfunding.service;

import com.atguigu.atcrowdfuning.bean.TMenu;

import java.util.List;

/**
 * @author erdong
 * @create 2019-09-10 20:23
 */
public interface TMenuService {
    List<TMenu> listMenus();

    void deleteMenu(Integer id);

    void saveMenu(TMenu menu);

    void updateMenu(TMenu menu);

    TMenu getMenu(Integer id);
}
