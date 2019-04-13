package com.framework.modules.sys.controller;


import com.framework.common.utils.R;
import com.framework.modules.sys.pojo.Menu;
import com.framework.modules.sys.pojo.User;
import com.framework.modules.sys.service.IMenuService;
import com.framework.modules.sys.vo.MenuVO;
import com.framework.modules.sys.vo.UserVO;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/menus")
    public R getMenu(){

        return R.ok().putResult(menuService.getMenuList());
    }

    @PostMapping("/menus/save")
    public R saveMenu(@RequestBody MenuVO menuVO){
        Menu menu = menuVO.getPojoMenu();
        int rst = menuService.saveMenu(menu);
        return R.ok();
    }

    @PostMapping("/menus/delete")
    public R deleteMenu(@RequestParam Long id){
         menuService.deleteMenu(id);
        return R.ok();
    }

}
