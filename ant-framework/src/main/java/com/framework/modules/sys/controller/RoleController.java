package com.framework.modules.sys.controller;


import com.framework.common.utils.PageUtils;
import com.framework.common.utils.R;
import com.framework.common.utils.RequestUtils;
import com.framework.modules.sys.pojo.Menu;
import com.framework.modules.sys.pojo.Role;
import com.framework.modules.sys.pojo.User;
import com.framework.modules.sys.service.IRoleService;
import com.framework.modules.sys.vo.RoleVO;
import com.framework.modules.sys.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 系统角色 前端控制器
 * </p>
 *
 * @author Mark
 * @since 2018-10-05
 */
@RestController
@RequestMapping("/sys")
public class RoleController {

    @Autowired
    IRoleService iRoleService;

    @GetMapping("/roles")
    public R getRoles(){

        return R.ok().putResult(iRoleService.getRoles());
    }

    @PostMapping("/roles/page")
    public R getRoleList(@RequestBody RequestUtils<RoleVO> params){
        PageUtils page = iRoleService.queryPage(params);
        return R.ok().putResult(page);
    }

    @DeleteMapping("/roles/delete")
    public R deleteRole(@RequestParam List<Long> roleIds){
        int rst = iRoleService.deleteRoles(roleIds);
        return R.ok().putResult(rst);
    }

    @PostMapping("/roles/save")
    public R saveRole(@RequestBody RoleVO roleVO){
        Role role = roleVO.getPojoRole();
        int rst = iRoleService.saveRole(role);
        return R.ok();
    }

    @GetMapping("/roles/{id}")
    public R getRole(@PathVariable(name="id", required = true) Long id){

        Role role = iRoleService.getRole(id);

        return R.ok().putResult(role);
    }


    @GetMapping("/roles/number/{number}")
    public R getRoleByNumber(@PathVariable(name="number", required = true) String path){

        Role role = iRoleService.getRoleByNumber(path);

        return R.ok().putResult(role);
    }

    @PostMapping(value = "/roles/getRolesByCondition")
    public R getRolesByCondition(@RequestBody RoleVO roleVO) throws Exception {

        List<Role> roles = iRoleService.getRolesByCondition(roleVO);

        return R.ok().putResult(roles);
    }
}
