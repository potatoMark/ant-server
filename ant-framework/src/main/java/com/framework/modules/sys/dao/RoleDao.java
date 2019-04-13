package com.framework.modules.sys.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.framework.modules.sys.pojo.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.framework.modules.sys.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

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

    public List<Role> loadRoles();


    IPage<Role> loadRolePage(@Param("pg") Page page, @Param("tb") Map<String, Object> param );
}
