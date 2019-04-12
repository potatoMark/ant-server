package com.framework.modules.sys.service.impl;

import com.framework.modules.sys.pojo.Role;
import com.framework.modules.sys.dao.RoleDao;
import com.framework.modules.sys.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 系统角色 服务实现类
 * </p>
 *
 * @author Mark
 * @since 2018-10-05
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements IRoleService {

    @Autowired
    RoleDao roleDao;

    @Override
    public List<Role> getRoles() {
        return roleDao.loadRoles();
    }
}
