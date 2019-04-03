package com.framework.modules.demo.controller;


import com.framework.common.utils.R;
import com.framework.modules.demo.service.IDragListService;
import com.framework.modules.demo.service.ITablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Mark
 * @since 2018-11-03
 */
@RestController
@RequestMapping("/demo")
public class TablesController {

    @Autowired
    ITablesService tablesService;

    @GetMapping("/tables")
    public R getUsers(){

        return R.ok().putResult(tablesService.list(null));
    }

}
