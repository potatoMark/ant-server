package com.framework.modules.sys.service.impl;

import com.framework.modules.sys.pojo.Menu;
import com.framework.modules.sys.dao.MenuDao;
import com.framework.modules.sys.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<Menu> getMenuList(Integer id) {

        return menuDao.getMenuList(id);
    }

    @Override
    public List<Menu> getMenuListWhereParam(String roleNumber) {
        return menuDao.getMenuListWhereParam(roleNumber);
    }
}
