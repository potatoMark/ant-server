package com.framework.modules.tool.controller;


import com.framework.common.utils.PageUtils;
import com.framework.common.utils.R;
import com.framework.common.utils.RequestUtils;
import com.framework.modules.tool.pojo.ToolDynamicTab;
import com.framework.modules.tool.vo.ToolDynamicTabVO;
import com.framework.modules.tool.service.IToolDynamicTabService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 动态标签 前端控制器
 * </p>
 *
 * @author mark
 * @email mark.make@hotmail.com
 * @since 2019-05-08T15:12:23.900Z
 */
 @Api("动态标签")
@RestController
@RequestMapping("/tool")
public class ToolDynamicTabController {

    @Autowired
    IToolDynamicTabService toolDynamicTabService;

    @GetMapping("/toolDynamicTabs")
    public R getToolDynamicTabs(){

        return R.ok().putResult(toolDynamicTabService.getToolDynamicTabs());
    }

    @ApiOperation("按照分页的方式查询动态标签信息")
    @ApiImplicitParam(name = "params", value = "查询条件", dataType = "RequestUtils<ToolDynamicTabVO>")
    @PostMapping("/toolDynamicTabs/page")
    public R getToolDynamicTabList(@RequestBody RequestUtils<ToolDynamicTabVO> params){
        PageUtils page = toolDynamicTabService.queryPage(params);
        return R.ok().putResult(page);
    }

    @ApiOperation("批量删除动态标签")
    @ApiImplicitParam(name = "toolDynamicTabIds", value = "多个Id信息", dataType = "List<Long>")
    @DeleteMapping("/toolDynamicTabs/delete")
    public R deleteToolDynamicTab(@RequestParam List<Long> toolDynamicTabIds){
        int rst = toolDynamicTabService.deleteToolDynamicTabs(toolDynamicTabIds);
        return R.ok().putResult(rst);
    }

    @ApiOperation("更新/新增动态标签")
    @ApiImplicitParam(name = "toolDynamicTabVO", value = "动态标签传输VO", dataType = "ToolDynamicTabVO")
    @PostMapping("/toolDynamicTabs/save")
    public R saveToolDynamicTab(@RequestBody ToolDynamicTabVO toolDynamicTabVO){
        ToolDynamicTab toolDynamicTab = toolDynamicTabVO.getPojoToolDynamicTab();
        int rst = toolDynamicTabService.saveToolDynamicTab(toolDynamicTabVO);
        return R.ok();
    }

    @ApiOperation("根据id查询动态标签")
    @ApiImplicitParam(name = "id", value = "ID", dataType = "Long")
    @GetMapping("/toolDynamicTabs/{id}")
    public R getToolDynamicTab(@PathVariable(name="id", required = true) Long id){

        ToolDynamicTabVO toolDynamicTabVO = toolDynamicTabService.getToolDynamicTab(id);

        return R.ok().putResult(toolDynamicTabVO);
    }


    @ApiOperation("根据条件查询动态标签")
    @ApiImplicitParam(name = "toolDynamicTabVO", value = "动态标签传输VO", dataType = "ToolDynamicTabVO")
    @PostMapping(value = "/toolDynamicTabs/getToolDynamicTabsByCondition")
    public R getToolDynamicTabsByCondition(@RequestBody ToolDynamicTabVO toolDynamicTabVO) throws Exception {

        List<ToolDynamicTab> toolDynamicTabs = toolDynamicTabService.getToolDynamicTabsByCondition(toolDynamicTabVO);

        return R.ok().putResult(toolDynamicTabs);
    }
}
