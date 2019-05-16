package com.framework.modules.dev.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.framework.common.utils.PageUtils;
import com.framework.common.utils.PojoUtils;
import com.framework.common.utils.RequestUtils;
import com.framework.common.entity.DynamicTabVO;
import com.framework.modules.dev.dao.DevCarDao;
import com.framework.modules.dev.pojo.DevCar;
import com.framework.modules.dev.service.IDevCarService;
import com.framework.modules.dev.vo.DevCarVO;
import com.framework.modules.tool.dao.ToolDynamicTabDao;
import com.framework.modules.tool.pojo.ToolDynamicTab;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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

    @Autowired
    ToolDynamicTabDao toolDynamicTabDao;

    @Override
    public List<DevCar> getDevCars() throws Exception{
        return devCarDao.selectList(null);
    }

    @Override
    public PageUtils queryPage(RequestUtils params) throws Exception{
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
    public void deleteDevCars(List<Long> devCarIds) throws Exception{

       devCarDao.deleteBatchIds(devCarIds);

        //查看是否存在自定义tab
        List<ToolDynamicTab> list = toolDynamicTabDao.queryListByTableName("dev_car");
        if(list != null){
            for (Long devCarId : devCarIds) {
                for (ToolDynamicTab toolDynamicTab : list) {
                    String sql = PojoUtils.generateDeleteSql(toolDynamicTab,"car_id", devCarId);
                    toolDynamicTabDao.dmlDynamicTable(sql);
                }
            }

        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveDevCar(DevCar devCar)throws Exception {
        PojoUtils.changeDate(devCar);
        if (devCar.getId() != null) {
             devCarDao.updateById(devCar);
        } else {
             devCarDao.insert(devCar);
        }
        //生成动态SQL
        for (DynamicTabVO dynamicTab : devCar.getDynamicTabs()) {

            String sql = PojoUtils.generateDynamicSql(dynamicTab,"car_id", devCar.getId());
            if (StringUtils.isNotBlank(sql)) {
                toolDynamicTabDao.dmlDynamicTable(sql);
            }
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public DevCar getDevCar(Long id) throws Exception{
        DevCar devCar = new DevCar();

        if (id != null && id != 0L) devCar = devCarDao.selectById(id);

        //查看是否存在自定义tab
        List<ToolDynamicTab> list = toolDynamicTabDao.queryListByTableName("dev_car");
        if (id != 0L) {
            list.forEach(item->{
                Map<String,Object> map = toolDynamicTabDao.queryDynamicByFK(item.getTabTableNumber(),"car_id",id);
                PojoUtils.dynamicSetValue(item,map);
            });
        }
        List<DynamicTabVO> dynamicTabVOS = PojoUtils.dynamicPojoToVo(list);

        devCar.setDynamicTabs(dynamicTabVOS);

        return devCar;
        

    }

    @Override
    public List<DevCar> getDevCarsByCondition(DevCarVO devCarVO) throws Exception{

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
