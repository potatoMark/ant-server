package com.framework.modules.dev.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.framework.common.utils.PageUtils;
import com.framework.common.utils.PojoUtils;
import com.framework.common.utils.RequestUtils;
import com.framework.modules.dev.pojo.DevCar;
import com.framework.modules.dev.vo.DevCarVO;
import com.framework.modules.dev.service.IDevCarService;
import com.framework.modules.dev.dao.DevCarDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 汽车 服务实现类
 * </p>
 *
 * @author mark
 * @email mark.make@hotmail.com
 * @since 2019-05-07T07:32:19.931Z
 */
@Service
public class DevCarServiceImpl extends com.baomidou.mybatisplus.extension.service.impl.ServiceImpl<DevCarDao, DevCar> implements IDevCarService {

    @Autowired
    DevCarDao devCarDao;

    @Override
    public List<DevCar> getDevCars() {
        return devCarDao.selectList(null);
    }

    @Override
    public PageUtils queryPage(RequestUtils params) {
        DevCarVO devCarVO = (DevCarVO)params.getCondition();
        Page<DevCar> page = (Page<DevCar>)devCarDao.selectPage(PageUtils.instance(params.getPage()),new QueryWrapper<DevCar>()
                                                                                        .like(StringUtils.isNotBlank(devCarVO.getNumber()),"number",devCarVO.getNumber())
                                                                                            .like(StringUtils.isNotBlank(devCarVO.getName()),"name",devCarVO.getName())
                                                                                            .like(devCarVO.getStatus() != null,"status",devCarVO.getStatus())
                                                                                            .like(devCarVO.getRepairtime() != null,"repairTime",devCarVO.getRepairtime())
                                                                                            .like(StringUtils.isNotBlank(devCarVO.getCreateuser()),"createuser",devCarVO.getCreateuser())
                                                                                            .like(StringUtils.isNotBlank(devCarVO.getUpdateuser()),"updateuser",devCarVO.getUpdateuser())
                                                                                            .like(devCarVO.getCreatedate() != null,"createdate",devCarVO.getCreatedate())
                                                                                            .like(devCarVO.getUpdatedate() != null,"updatedate",devCarVO.getUpdatedate())
                                                    );
        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteDevCars(List<Long> devCarIds) {


                    return devCarDao.deleteBatchIds(devCarIds);
        

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveDevCar(DevCar devCar) {
        PojoUtils.changeDate(devCar);
                    if (devCar.getId() != null) {
                int rst = devCarDao.updateById(devCar);
                return rst;
            } else {
                int rst = devCarDao.insert(devCar);
                return rst;
            }
        
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DevCar getDevCar(Long id) {

                    return devCarDao.selectById(id);
        

    }

    @Override
    public List<DevCar> getDevCarsByCondition(DevCarVO devCarVO) {

        return devCarDao.selectList(new QueryWrapper<DevCar>()
                                                                .like(StringUtils.isNotBlank(devCarVO.getNumber()),"number",devCarVO.getNumber())
                                                                    .like(StringUtils.isNotBlank(devCarVO.getName()),"name",devCarVO.getName())
                                                                    .like(devCarVO.getStatus() != null,"status",devCarVO.getStatus())
                                                                    .like(devCarVO.getRepairtime() != null,"repairTime",devCarVO.getRepairtime())
                                                                    .like(StringUtils.isNotBlank(devCarVO.getCreateuser()),"createuser",devCarVO.getCreateuser())
                                                                    .like(StringUtils.isNotBlank(devCarVO.getUpdateuser()),"updateuser",devCarVO.getUpdateuser())
                                                                    .like(devCarVO.getCreatedate() != null,"createdate",devCarVO.getCreatedate())
                                                                    .like(devCarVO.getUpdatedate() != null,"updatedate",devCarVO.getUpdatedate())
                                        );
    }
}
