package com.framework.modules.sys.service;

import com.framework.modules.sys.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.framework.modules.sys.pojo.User;

import java.util.List;

/**
 * <p>
 * 系统角色 服务类
 * </p>
 *
 * @author Mark
 * @since 2018-10-05
 */
public interface IRoleService extends IService<Role> {

    public List<Role> getRoles();

}
