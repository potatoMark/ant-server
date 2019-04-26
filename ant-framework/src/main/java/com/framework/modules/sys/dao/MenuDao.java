package com.framework.modules.sys.dao;

import com.framework.modules.sys.pojo.Menu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统菜单 Mapper 接口
 * </p>
 *
 * @author Mark
 * @since 2018-10-05
 */
public interface MenuDao extends BaseMapper<Menu> {

    public List<Menu> getMenuList();
    public List<Menu> getChildrenMenu(Long id);
    public Menu getMenuListById(Long id);
    public List<Menu> getMenuListWhereParam(String roleNumber);

    public List<Menu> getRoleMenus(Long roleId);
    public List<Menu> getMenuListByRoleNumbers(List<String> roleNumbers);
}
