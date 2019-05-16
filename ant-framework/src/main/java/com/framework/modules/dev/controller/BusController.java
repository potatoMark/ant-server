package com.framework.modules.dev.controller;


import com.framework.common.utils.PageUtils;
import com.framework.common.utils.R;
import com.framework.common.utils.RequestUtils;
import com.framework.modules.dev.pojo.Bus;
import com.framework.modules.dev.vo.BusVO;
import com.framework.modules.dev.service.IBusService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 公车 前端控制器
 * </p>
 *
 * @author mark
 * @email mark.make@hotmail.com
 * @since 2019-05-16T15:40:35.080Z
 */
 @Api("公车")
@RestController
@RequestMapping("/dev")
public class BusController {

    @Autowired
    IBusService busService;

    @GetMapping("/buss")
    public R getBuss()throws Exception{

        return R.ok().putResult(busService.getBuss());
    }

    @ApiOperation("按照分页的方式查询公车信息")
    @ApiImplicitParam(name = "params", value = "查询条件", dataType = "RequestUtils<BusVO>")
    @PostMapping("/buss/page")
    public R getBusList(@RequestBody RequestUtils<BusVO> params)throws Exception{
        PageUtils page = busService.queryPage(params);
        return R.ok().putResult(page);
    }

    @ApiOperation("批量删除公车")
    @ApiImplicitParam(name = "busIds", value = "多个Id信息", dataType = "List<Long>")
    @DeleteMapping("/buss/delete")
    public R deleteBus(@RequestParam List<Long> busIds)throws Exception{
        busService.deleteBuss(busIds);
        return R.ok();
    }

    @ApiOperation("更新/新增公车")
    @ApiImplicitParam(name = "busVO", value = "公车传输VO", dataType = "BusVO")
    @PostMapping("/buss/save")
    public R saveBus(@RequestBody BusVO busVO)throws Exception{
        Bus bus = busVO.getPojoBus();
        busService.saveBus(bus);
        return R.ok();
    }

    @ApiOperation("根据id查询公车")
    @ApiImplicitParam(name = "id", value = "ID", dataType = "Long")
    @GetMapping("/buss/{id}")
    public R getBus(@PathVariable(name="id", required = true) Long id)throws Exception{

        Bus bus = busService.getBus(id);

        return R.ok().putResult(bus);
    }


    @ApiOperation("根据条件查询公车")
    @ApiImplicitParam(name = "busVO", value = "公车传输VO", dataType = "BusVO")
    @PostMapping(value = "/buss/getBussByCondition")
    public R getBussByCondition(@RequestBody BusVO busVO) throws Exception {

        List<Bus> buss = busService.getBussByCondition(busVO);

        return R.ok().putResult(buss);
    }
}
