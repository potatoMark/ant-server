package com.framework.modules.sys.service;

import com.framework.modules.sys.pojo.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 系统菜单 服务类
 * </p>
 *
 * @author Mark
 * @since 2018-10-05
 */
public interface IMenuService extends IService<Menu> {

    public List<Menu> getMenuList();
    public List<Menu> getMenuListWhereParam(String roleNumber);
    public int saveMenu(Menu menu);
    public void deleteMenu(Long id);
    public List<Menu> getMenuListByRoles(List<String> roleNumbers);

    public Menu getMenuByPath(String path);
}
