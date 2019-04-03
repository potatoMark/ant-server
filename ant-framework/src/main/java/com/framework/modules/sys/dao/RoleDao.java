package com.framework.modules.sys.dao;

import com.framework.modules.sys.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 系统角色 Mapper 接口
 * </p>
 *
 * @author Mark
 * @since 2018-10-05
 */
public interface RoleDao extends BaseMapper<Role> {

    public List<Role> findByUserId(Long id);

    public List<Role> findByMenuId(Long id);

    public Role findById(Long id);
}
