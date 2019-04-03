package com.framework.modules.sys.controller;


import com.framework.common.utils.R;
import com.framework.modules.sys.pojo.User;
import com.framework.modules.sys.service.IMenuService;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 系统菜单 前端控制器
 * </p>
 *
 * @author Mark
 * @since 2018-10-10
 */
@RestController
@RequestMapping("/sys")
public class MenuController {

    @Autowired
    IMenuService menuService;

    @GetMapping("/menusByParam")
    public R getMenusWhereParam(@RequestParam("roleNumber")String roleNumber){

        return R.ok().putResult(menuService.getMenuListWhereParam(roleNumber));
    }

    @GetMapping("/menus/{id}")
    public R getMenu(@PathVariable(name="id", required = true) Integer id){

        return R.ok().putResult(menuService.getMenuList(id));
    }

}
