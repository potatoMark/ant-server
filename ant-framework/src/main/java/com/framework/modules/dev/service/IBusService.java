package com.framework.modules.dev.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.framework.common.utils.PageUtils;
import com.framework.common.utils.RequestUtils;
import com.framework.modules.dev.pojo.Bus;
import com.framework.modules.dev.vo.BusVO;

import java.util.List;

/**
 * <p>
 * 公车 Service
 * </p>
 *
 * @author mark
 * @email mark.make@hotmail.com
 * @since 2019-05-17T02:00:23.993Z
 */
public interface IBusService extends IService<Bus> {

    public List<Bus> getBuss() throws Exception;

    PageUtils queryPage(RequestUtils params) throws Exception;

    public void deleteBuss(List<Long> busIds) throws Exception;

    public void saveBus(Bus bus) throws Exception;

    public Bus getBus(Long id) throws Exception;

    public List<Bus> getBussByCondition(BusVO busVO) throws Exception;
}
