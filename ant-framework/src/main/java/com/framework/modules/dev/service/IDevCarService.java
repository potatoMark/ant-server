package com.framework.modules.dev.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.framework.common.utils.PageUtils;
import com.framework.common.utils.RequestUtils;
import com.framework.modules.dev.pojo.DevCar;
import com.framework.modules.dev.vo.DevCarVO;

import java.util.List;

/**
 * <p>
 * 汽车 Service
 * </p>
 *
 * @author mark
 * @email mark.make@hotmail.com
 * @since 2019-05-07T07:32:19.931Z
 */
public interface IDevCarService extends IService<DevCar> {

    public List<DevCar> getDevCars() throws Exception;

    PageUtils queryPage(RequestUtils params) throws Exception;

    public void deleteDevCars(List<Long> devCarIds) throws Exception;

    public void saveDevCar(DevCar devCar) throws Exception;

    public DevCar getDevCar(Long id) throws Exception;

    public List<DevCar> getDevCarsByCondition(DevCarVO devCarVO) throws Exception;;
}
