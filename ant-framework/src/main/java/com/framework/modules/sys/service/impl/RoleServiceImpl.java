package com.framework.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.framework.common.utils.PageUtils;
import com.framework.common.utils.RequestUtils;
import com.framework.modules.sys.dao.RoleMenuDao;
import com.framework.modules.sys.pojo.*;
import com.framework.modules.sys.dao.RoleDao;
import com.framework.modules.sys.service.IRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.framework.modules.sys.vo.RoleVO;
import com.framework.modules.sys.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    RoleMenuDao roleMenuDao;

    @Override
    public List<Role> getRoles() {
        return roleDao.loadRoles();
    }

    @Override
    public PageUtils queryPage(RequestUtils params) {
        RoleVO roleVO = (RoleVO)params.getCondition();
        Page<Role> page = (Page<Role>)roleDao.selectPage(PageUtils.instance(params.getPage()),new QueryWrapper<Role>()
                .like(StringUtils.isNotBlank(roleVO.getNumber()),"number",roleVO.getNumber())
                .like(StringUtils.isNotBlank(roleVO.getName()),"name",roleVO.getName())
        );
        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteRoles(List<Long> roleIds) {
        for (Long roleId : roleIds) {
            roleMenuDao.delete(new QueryWrapper<RoleMenu>().eq("role_id",roleId));
        }
        return roleDao.deleteBatchIds(roleIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveRole(Role role) {

        if (role.getId() != null) {
            int rst = roleDao.updateById(role);
            roleMenuDao.delete(new QueryWrapper<RoleMenu>().eq("role_id",role.getId()));
            if (role.getMenus() != null) {
                for (Menu menu : role.getMenus()) {
                    RoleMenu roleMenu = new RoleMenu();
                    roleMenu.setMenuId(menu.getId());
                    roleMenu.setRoleId(role.getId());
                    roleMenuDao.insert(roleMenu);
                }
            }
            return rst;
        } else {

            int rst = roleDao.insert(role);
            if (role.getMenus() != null) {
                for (Menu menu : role.getMenus()) {
                    RoleMenu roleMenu = new RoleMenu();
                    roleMenu.setMenuId(menu.getId());
                    roleMenu.setRoleId(role.getId());
                    roleMenuDao.insert(roleMenu);
                }
            }

            return rst;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Role getRole(Long id) {
        return roleDao.findById(id);
    }

    @Override
    public Role getRoleByNumber(String number) {
        return roleDao.selectOne(new QueryWrapper<Role>().eq("number",number));
    }

    @Override
    public List<Role> getRolesByCondition(RoleVO roleVO) {

        return roleDao.selectList(new QueryWrapper<Role>()
                .like(StringUtils.isNotBlank(roleVO.getNumber()),"number",roleVO.getNumber())
                .like(StringUtils.isNotBlank(roleVO.getName()),"name",roleVO.getName())
        );
    }
}
