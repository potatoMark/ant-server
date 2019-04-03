package com.framework.modules.demo.controller;


import com.framework.common.utils.R;
import com.framework.modules.demo.service.IDragListService;
import com.framework.modules.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 拖拽列表 前端控制器
 * </p>
 *
 * @author Mark
 * @since 2018-11-03
 */
@RestController
@RequestMapping("/demo")
public class DragListController {

    @Autowired
    IDragListService dragListService;

    @GetMapping("/drag_list")
    public R getUsers(){

        return R.ok().putResult(dragListService.list(null));
    }

}
