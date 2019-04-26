package com.framework.modules.sys.service;

import com.framework.common.utils.PageUtils;
import com.framework.common.utils.RequestUtils;
import com.framework.modules.sys.pojo.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import com.framework.modules.sys.pojo.User;
import com.framework.modules.sys.vo.RoleVO;
import com.framework.modules.sys.vo.UserVO;

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

    PageUtils queryPage(RequestUtils params);

    public int deleteRoles(List<Long> roleIds);

    public int saveRole(Role role);

    public Role getRole(Long id);

    public Role getRoleByNumber(String number);

    public List<Role> getRolesByCondition(RoleVO roleVO);
}
