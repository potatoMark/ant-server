package com.framework.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.framework.modules.sys.pojo.Menu;
import com.framework.modules.sys.dao.MenuDao;
import com.framework.modules.sys.pojo.UserRole;
import com.framework.modules.sys.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 系统菜单 服务实现类
 * </p>
 *
 * @author Mark
 * @since 2018-10-05
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuDao, Menu> implements IMenuService {

    @Autowired
    MenuDao menuDao;

    @Override
    public List<Menu> getMenuList() {

        return menuDao.getMenuList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public List<Menu> getMenuListWhereParam(String roleNumber) {
        return menuDao.getMenuListWhereParam(roleNumber);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveMenu(Menu menu) {
        if (menu.getId() == null) {
            menuDao.insert(menu);
        }
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteMenu(Integer id) {

        Menu menu = menuDao.getMenuListById(id);
        List<Menu> menus  = new ArrayList<>();
        menus.add(menu);
        deleteParentChildren(menus);
    }

    public void deleteParentChildren(List<Menu> menus){

        for (Menu menu : menus) {
            List<Menu> childrenMenus = menu.getChildren();
            if (childrenMenus != null && childrenMenus.size() > 0) {
                this.deleteParentChildren(childrenMenus);
            }

            menuDao.deleteById(menu.getId());

        }
    }
}
