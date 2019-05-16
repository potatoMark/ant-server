package com.framework.modules.dev.controller;


import com.framework.common.utils.PageUtils;
import com.framework.common.utils.R;
import com.framework.common.utils.RequestUtils;
import com.framework.modules.dev.pojo.DevCar;
import com.framework.modules.dev.vo.DevCarVO;
import com.framework.modules.dev.service.IDevCarService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 汽车 前端控制器
 * </p>
 *
 * @author mark
 * @email mark.make@hotmail.com
 * @since 2019-05-07T07:32:19.931Z
 */
 @Api("汽车")
@RestController
@RequestMapping("/dev")
public class DevCarController {

    @Autowired
    IDevCarService devCarService;

    @GetMapping("/devCars")
    public R getDevCars() throws Exception{

        return R.ok().putResult(devCarService.getDevCars());
    }

    @ApiOperation("按照分页的方式查询汽车信息")
    @ApiImplicitParam(name = "params", value = "查询条件", dataType = "RequestUtils<DevCarVO>")
    @PostMapping("/devCars/page")
    public R getDevCarList(@RequestBody RequestUtils<DevCarVO> params) throws Exception{
        PageUtils page = devCarService.queryPage(params);
        return R.ok().putResult(page);
    }

    @ApiOperation("批量删除汽车")
    @ApiImplicitParam(name = "devCarIds", value = "多个Id信息", dataType = "List<Long>")
    @DeleteMapping("/devCars/delete")
    public R deleteDevCar(@RequestParam List<Long> devCarIds) throws Exception{
        devCarService.deleteDevCars(devCarIds);
        return R.ok();
    }

    @ApiOperation("更新/新增汽车")
    @ApiImplicitParam(name = "devCarVO", value = "汽车传输VO", dataType = "DevCarVO")
    @PostMapping("/devCars/save")
    public R saveDevCar(@RequestBody DevCarVO devCarVO) throws Exception{
        DevCar devCar = devCarVO.getPojoDevCar();
        devCarService.saveDevCar(devCar);
        return R.ok();
    }

    @ApiOperation("根据id查询汽车")
    @ApiImplicitParam(name = "id", value = "ID", dataType = "Long")
    @GetMapping("/devCars/{id}")
    public R getDevCar(@PathVariable(name="id") Long id) throws Exception{

        DevCar devCar = devCarService.getDevCar(id);

        return R.ok().putResult(devCar);
    }


    @ApiOperation("根据条件查询汽车")
    @ApiImplicitParam(name = "devCarVO", value = "汽车传输VO", dataType = "DevCarVO")
    @PostMapping(value = "/devCars/getDevCarsByCondition")
    public R getDevCarsByCondition(@RequestBody DevCarVO devCarVO) throws Exception {

        List<DevCar> devCars = devCarService.getDevCarsByCondition(devCarVO);

        return R.ok().putResult(devCars);
    }
}
