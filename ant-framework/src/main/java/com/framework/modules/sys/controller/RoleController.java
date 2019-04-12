package com.framework.modules.sys.controller;


import com.framework.common.utils.R;
import com.framework.modules.sys.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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
}
