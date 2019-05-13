package com.framework.modules.tool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.framework.common.utils.PageUtils;
import com.framework.common.utils.PojoUtils;
import com.framework.common.utils.RequestUtils;
import com.framework.modules.tool.dao.ToolDynamicTabComponentDao;
import com.framework.modules.tool.pojo.ToolDynamicTab;
import com.framework.modules.tool.pojo.ToolDynamicTabComponent;
import com.framework.modules.tool.vo.ToolDynamicTabComponentVO;
import com.framework.modules.tool.vo.ToolDynamicTabVO;
import com.framework.modules.tool.service.IToolDynamicTabService;
import com.framework.modules.tool.dao.ToolDynamicTabDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 动态标签 服务实现类
 * </p>
 *
 * @author mark
 * @email mark.make@hotmail.com
 * @since 2019-05-08T15:12:23.900Z
 */
@Service
public class ToolDynamicTabServiceImpl extends com.baomidou.mybatisplus.extension.service.impl.ServiceImpl<ToolDynamicTabDao, ToolDynamicTab> implements IToolDynamicTabService {

    @Autowired
    ToolDynamicTabDao toolDynamicTabDao;

    @Autowired
    ToolDynamicTabComponentDao toolDynamicTabComponentDao;

    @Override
    public List<ToolDynamicTab> getToolDynamicTabs() {
        return toolDynamicTabDao.selectList(null);
    }

    @Override
    public PageUtils queryPage(RequestUtils params) {
        ToolDynamicTabVO toolDynamicTabVO = (ToolDynamicTabVO)params.getCondition();
        Page<ToolDynamicTab> page = (Page<ToolDynamicTab>)toolDynamicTabDao.selectPage(PageUtils.instance(params.getPage()),new QueryWrapper<ToolDynamicTab>()
                                    .like(StringUtils.isNotBlank(toolDynamicTabVO.getTableName()),"table_name",toolDynamicTabVO.getTableName())
                                        .like(StringUtils.isNotBlank(toolDynamicTabVO.getTabTableNumber()),"tab_table_number",toolDynamicTabVO.getTabTableNumber())
                                        .like(StringUtils.isNotBlank(toolDynamicTabVO.getTabTableName()),"tab_table_name",toolDynamicTabVO.getTabTableName())
                                        .like(toolDynamicTabVO.getActive() != null,"active",toolDynamicTabVO.getActive())
                                                    );
        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteToolDynamicTabs(List<Long> toolDynamicTabIds) {

        toolDynamicTabDao.deleteBatchIds(toolDynamicTabIds);

        for (Long id : toolDynamicTabIds) {
            toolDynamicTabComponentDao.delete(new QueryWrapper<ToolDynamicTabComponent>().eq("dynamic_tab_id",id));
        }
        return 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveToolDynamicTab(ToolDynamicTabVO toolDynamicTabVO) {
        ToolDynamicTab toolDynamicTab = toolDynamicTabVO.getPojoToolDynamicTab();
        List<ToolDynamicTabComponentVO> list = toolDynamicTabVO.getToolDynamicTabComponentVOS();
        PojoUtils.changeDate(toolDynamicTab);
        int rst ;
        if (toolDynamicTab.getId() != null) {
            rst = toolDynamicTabDao.updateById(toolDynamicTab);
            toolDynamicTabComponentDao.delete(new QueryWrapper<ToolDynamicTabComponent>().eq("dynamic_tab_id",toolDynamicTab.getId()));
        } else {
             rst = toolDynamicTabDao.insert(toolDynamicTab);
        }

        list.forEach(item->{
            item.setDynamicTabId(toolDynamicTab.getId());
            toolDynamicTabComponentDao.insert(item.getPojoToolDynamicTabComponent());
        });

        return rst;

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ToolDynamicTabVO getToolDynamicTab(Long id) {
        ToolDynamicTab toolDynamicTab = toolDynamicTabDao.selectById(id);
        List<ToolDynamicTabComponent> list =  toolDynamicTabComponentDao.selectList(new QueryWrapper<ToolDynamicTabComponent>().eq("dynamic_tab_id",id));
        List<ToolDynamicTabComponentVO> listVo = new ArrayList<>();
        list.forEach(item->{
            ToolDynamicTabComponentVO v = new ToolDynamicTabComponentVO();
            v.setContentNumber(item.getContentNumber());
            v.setContentName(item.getContentName());
            v.setContentItem(item.getContentItem());
            v.setContentRequired(item.getContentRequired());
            v.setContentType(item.getContentType());
            v.setId(item.getId());
            v.setDynamicTabId(item.getDynamicTabId());
            listVo.add(v);
        });
        ToolDynamicTabVO toolDynamicTabVO = new ToolDynamicTabVO();
        toolDynamicTabVO.setId(toolDynamicTab.getId());
        toolDynamicTabVO.setActive(toolDynamicTab.getActive());
        toolDynamicTabVO.setTableName(toolDynamicTab.getTableName());
        toolDynamicTabVO.setTabTableName(toolDynamicTab.getTabTableName());
        toolDynamicTabVO.setTabTableNumber(toolDynamicTab.getTabTableNumber());
        toolDynamicTabVO.setCreatedate(toolDynamicTab.getCreatedate());
        toolDynamicTabVO.setCreateuser(toolDynamicTab.getCreateuser());
        toolDynamicTabVO.setUpdatedate(toolDynamicTab.getUpdatedate());
        toolDynamicTabVO.setUpdateuser(toolDynamicTab.getUpdateuser());

        toolDynamicTabVO.setToolDynamicTabComponentVOS(listVo);
                    return toolDynamicTabVO;
        

    }

    @Override
    public List<ToolDynamicTab> getToolDynamicTabsByCondition(ToolDynamicTabVO toolDynamicTabVO) {

        return toolDynamicTabDao.selectList(new QueryWrapper<ToolDynamicTab>()
            .like(StringUtils.isNotBlank(toolDynamicTabVO.getTableName()),"table_name",toolDynamicTabVO.getTableName())
                .like(StringUtils.isNotBlank(toolDynamicTabVO.getTabTableNumber()),"tab_table_number",toolDynamicTabVO.getTabTableNumber())
                .like(StringUtils.isNotBlank(toolDynamicTabVO.getTabTableName()),"tab_table_name",toolDynamicTabVO.getTabTableName())
                .like(toolDynamicTabVO.getActive() != null,"active",toolDynamicTabVO.getActive())
                                        );
    }
}
