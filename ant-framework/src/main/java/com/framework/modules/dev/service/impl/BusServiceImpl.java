package com.framework.modules.dev.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.framework.common.utils.PageUtils;
import com.framework.common.utils.PojoUtils;
import com.framework.common.utils.RequestUtils;
import com.framework.modules.dev.pojo.Bus;
import com.framework.modules.dev.vo.BusVO;
import com.framework.modules.dev.service.IBusService;
import com.framework.modules.dev.dao.BusDao;
import com.framework.common.entity.DynamicTabVO;
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
 * 公车 服务实现类
 * </p>
 *
 * @author mark
 * @email mark.make@hotmail.com
 * @since 2019-05-17T02:00:23.993Z
 */
@Service
public class BusServiceImpl extends com.baomidou.mybatisplus.extension.service.impl.ServiceImpl<BusDao, Bus> implements IBusService {

    @Autowired
    BusDao busDao;

    @Autowired
    ToolDynamicTabDao toolDynamicTabDao;

    @Override
    public List<Bus> getBuss()throws Exception {
        return busDao.selectList(null);
    }

    @Override
    public PageUtils queryPage(RequestUtils params)throws Exception {
        BusVO busVO = (BusVO)params.getCondition();
        Page<Bus> page = (Page<Bus>)busDao.selectPage(PageUtils.instance(params.getPage()),new QueryWrapper<Bus>()
                .like(StringUtils.isNotBlank(busVO.getNumber()),"number",busVO.getNumber())
                .like(StringUtils.isNotBlank(busVO.getName()),"name",busVO.getName())
                .like(busVO.getTesttime() != null,"testTime",busVO.getTesttime())
                .like(StringUtils.isNotBlank(busVO.getCreateuser()),"createuser",busVO.getCreateuser())
                .like(StringUtils.isNotBlank(busVO.getUpdateuser()),"updateuser",busVO.getUpdateuser())
                .like(busVO.getCreatedate() != null,"createdate",busVO.getCreatedate())
                .like(busVO.getUpdatedate() != null,"updatedate",busVO.getUpdatedate())
        );
        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBuss(List<Long> busIds)throws Exception {
        busDao.deleteBatchIds(busIds);

        //查看是否存在自定义tab
        List<ToolDynamicTab> list = toolDynamicTabDao.queryListByTableName("dev_bus");
        if(list != null){
            for (Long busId : busIds) {
                for (ToolDynamicTab toolDynamicTab : list) {
                    String sql = PojoUtils.generateDeleteSql(toolDynamicTab,"bus_id", busId);
                    toolDynamicTabDao.dmlDynamicTable(sql);
                }
            }
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveBus(Bus bus)throws Exception {
        PojoUtils.changeDate(bus);

        if (bus.getId() != null) {
           busDao.updateById(bus);
        } else {
           busDao.insert(bus);
        }
        //生成动态SQL
        if (bus.getDynamicTabs() != null) {
            for (DynamicTabVO dynamicTab : bus.getDynamicTabs()) {

                String sql = PojoUtils.generateDynamicSql(dynamicTab,"bus_id", bus.getId());
                if (StringUtils.isNotBlank(sql)) {
                    toolDynamicTabDao.dmlDynamicTable(sql);
                }
            }
        }

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Bus getBus(Long id)throws Exception {
        Bus bus = new Bus();

        if (id != null && id != 0L) bus = busDao.selectById(id);
        //查看是否存在自定义tab
        List<ToolDynamicTab> list = toolDynamicTabDao.queryListByTableName("dev_bus");
        if (id != 0L) {
            list.forEach(item->{
                Map<String,Object> map = toolDynamicTabDao.queryDynamicByFK(item.getTabTableNumber(),"bus_id",id);
                PojoUtils.dynamicSetValue(item,map);
            });
        }
        List<DynamicTabVO> dynamicTabVOS = PojoUtils.dynamicPojoToVo(list);
        bus.setDynamicTabs(dynamicTabVOS);

        return bus;
    }

    @Override
    public List<Bus> getBussByCondition(BusVO busVO)throws Exception {

        return busDao.selectList(new QueryWrapper<Bus>()
            .like(StringUtils.isNotBlank(busVO.getNumber()),"number",busVO.getNumber())
            .like(StringUtils.isNotBlank(busVO.getName()),"name",busVO.getName())
            .like(busVO.getTesttime() != null,"testTime",busVO.getTesttime())
            .like(StringUtils.isNotBlank(busVO.getCreateuser()),"createuser",busVO.getCreateuser())
            .like(StringUtils.isNotBlank(busVO.getUpdateuser()),"updateuser",busVO.getUpdateuser())
            .like(busVO.getCreatedate() != null,"createdate",busVO.getCreatedate())
            .like(busVO.getUpdatedate() != null,"updatedate",busVO.getUpdatedate())
        );
    }
}
