package com.framework.modules.tool.controller;


import com.framework.common.utils.PageUtils;
import com.framework.common.utils.R;
import com.framework.common.utils.RequestUtils;
import com.framework.modules.tool.pojo.ToolDynamicTabComponent;
import com.framework.modules.tool.vo.ToolDynamicTabComponentVO;
import com.framework.modules.tool.service.IToolDynamicTabComponentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 标签构成 前端控制器
 * </p>
 *
 * @author mark
 * @email mark.make@hotmail.com
 * @since 2019-05-10T07:32:55.744Z
 */
 @Api("标签构成")
@RestController
@RequestMapping("/tool")
public class ToolDynamicTabComponentController {

    @Autowired
    IToolDynamicTabComponentService toolDynamicTabComponentService;

    @GetMapping("/toolDynamicTabComponents")
    public R getToolDynamicTabComponents(){

        return R.ok().putResult(toolDynamicTabComponentService.getToolDynamicTabComponents());
    }

    @ApiOperation("按照分页的方式查询标签构成信息")
    @ApiImplicitParam(name = "params", value = "查询条件", dataType = "RequestUtils<ToolDynamicTabComponentVO>")
    @PostMapping("/toolDynamicTabComponents/page")
    public R getToolDynamicTabComponentList(@RequestBody RequestUtils<ToolDynamicTabComponentVO> params){
        PageUtils page = toolDynamicTabComponentService.queryPage(params);
        return R.ok().putResult(page);
    }

    @ApiOperation("批量删除标签构成")
    @ApiImplicitParam(name = "toolDynamicTabComponentIds", value = "多个Id信息", dataType = "List<Long>")
    @DeleteMapping("/toolDynamicTabComponents/delete")
    public R deleteToolDynamicTabComponent(@RequestParam List<Long> toolDynamicTabComponentIds){
        int rst = toolDynamicTabComponentService.deleteToolDynamicTabComponents(toolDynamicTabComponentIds);
        return R.ok().putResult(rst);
    }

    @ApiOperation("更新/新增标签构成")
    @ApiImplicitParam(name = "toolDynamicTabComponentVO", value = "标签构成传输VO", dataType = "ToolDynamicTabComponentVO")
    @PostMapping("/toolDynamicTabComponents/save")
    public R saveToolDynamicTabComponent(@RequestBody ToolDynamicTabComponentVO toolDynamicTabComponentVO){
        ToolDynamicTabComponent toolDynamicTabComponent = toolDynamicTabComponentVO.getPojoToolDynamicTabComponent();
        int rst = toolDynamicTabComponentService.saveToolDynamicTabComponent(toolDynamicTabComponent);
        return R.ok();
    }

    @ApiOperation("根据id查询标签构成")
    @ApiImplicitParam(name = "id", value = "ID", dataType = "Long")
    @GetMapping("/toolDynamicTabComponents/{id}")
    public R getToolDynamicTabComponent(@PathVariable(name="id", required = true) Long id){

        ToolDynamicTabComponent toolDynamicTabComponent = toolDynamicTabComponentService.getToolDynamicTabComponent(id);

        return R.ok().putResult(toolDynamicTabComponent);
    }


    @ApiOperation("根据条件查询标签构成")
    @ApiImplicitParam(name = "toolDynamicTabComponentVO", value = "标签构成传输VO", dataType = "ToolDynamicTabComponentVO")
    @PostMapping(value = "/toolDynamicTabComponents/getToolDynamicTabComponentsByCondition")
    public R getToolDynamicTabComponentsByCondition(@RequestBody ToolDynamicTabComponentVO toolDynamicTabComponentVO) throws Exception {

        List<ToolDynamicTabComponent> toolDynamicTabComponents = toolDynamicTabComponentService.getToolDynamicTabComponentsByCondition(toolDynamicTabComponentVO);

        return R.ok().putResult(toolDynamicTabComponents);
    }
}
