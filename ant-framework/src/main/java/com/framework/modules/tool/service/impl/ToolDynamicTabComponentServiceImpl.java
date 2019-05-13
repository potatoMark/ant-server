package com.framework.modules.tool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.framework.common.utils.PageUtils;
import com.framework.common.utils.PojoUtils;
import com.framework.common.utils.RequestUtils;
import com.framework.modules.tool.pojo.ToolDynamicTabComponent;
import com.framework.modules.tool.vo.ToolDynamicTabComponentVO;
import com.framework.modules.tool.service.IToolDynamicTabComponentService;
import com.framework.modules.tool.dao.ToolDynamicTabComponentDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 * 标签构成 服务实现类
 * </p>
 *
 * @author mark
 * @email mark.make@hotmail.com
 * @since 2019-05-10T07:32:55.744Z
 */
@Service
public class ToolDynamicTabComponentServiceImpl extends com.baomidou.mybatisplus.extension.service.impl.ServiceImpl<ToolDynamicTabComponentDao, ToolDynamicTabComponent> implements IToolDynamicTabComponentService {

    @Autowired
    ToolDynamicTabComponentDao toolDynamicTabComponentDao;

    @Override
    public List<ToolDynamicTabComponent> getToolDynamicTabComponents() {
        return toolDynamicTabComponentDao.selectList(null);
    }

    @Override
    public PageUtils queryPage(RequestUtils params) {
        ToolDynamicTabComponentVO toolDynamicTabComponentVO = (ToolDynamicTabComponentVO)params.getCondition();
        Page<ToolDynamicTabComponent> page = (Page<ToolDynamicTabComponent>)toolDynamicTabComponentDao.selectPage(PageUtils.instance(params.getPage()),new QueryWrapper<ToolDynamicTabComponent>()
                                                                                        .like(toolDynamicTabComponentVO.getDynamicTabId() != null,"dynamic_tab_id",toolDynamicTabComponentVO.getDynamicTabId())
                                                                                            .like(StringUtils.isNotBlank(toolDynamicTabComponentVO.getContentNumber()),"content_number",toolDynamicTabComponentVO.getContentNumber())
                                                                                            .like(StringUtils.isNotBlank(toolDynamicTabComponentVO.getContentName()),"content_name",toolDynamicTabComponentVO.getContentName())
                                                                                            .like(toolDynamicTabComponentVO.getContentRequired() != null,"content_required",toolDynamicTabComponentVO.getContentRequired())
                                                                                            .like(StringUtils.isNotBlank(toolDynamicTabComponentVO.getContentType()),"content_type",toolDynamicTabComponentVO.getContentType())
                                                                                            .like(StringUtils.isNotBlank(toolDynamicTabComponentVO.getContentItem()),"content_item",toolDynamicTabComponentVO.getContentItem())
                                                    );
        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteToolDynamicTabComponents(List<Long> toolDynamicTabComponentIds) {


                    return toolDynamicTabComponentDao.deleteBatchIds(toolDynamicTabComponentIds);
        

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveToolDynamicTabComponent(ToolDynamicTabComponent toolDynamicTabComponent) {
        PojoUtils.changeDate(toolDynamicTabComponent);
                    if (toolDynamicTabComponent.getId() != null) {
                int rst = toolDynamicTabComponentDao.updateById(toolDynamicTabComponent);
                return rst;
            } else {
                int rst = toolDynamicTabComponentDao.insert(toolDynamicTabComponent);
                return rst;
            }
        
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ToolDynamicTabComponent getToolDynamicTabComponent(Long id) {

                    return toolDynamicTabComponentDao.selectById(id);
        

    }

    @Override
    public List<ToolDynamicTabComponent> getToolDynamicTabComponentsByCondition(ToolDynamicTabComponentVO toolDynamicTabComponentVO) {

        return toolDynamicTabComponentDao.selectList(new QueryWrapper<ToolDynamicTabComponent>()
                                                                .like(toolDynamicTabComponentVO.getDynamicTabId() != null,"dynamic_tab_id",toolDynamicTabComponentVO.getDynamicTabId())
                                                                    .like(StringUtils.isNotBlank(toolDynamicTabComponentVO.getContentNumber()),"content_number",toolDynamicTabComponentVO.getContentNumber())
                                                                    .like(StringUtils.isNotBlank(toolDynamicTabComponentVO.getContentName()),"content_name",toolDynamicTabComponentVO.getContentName())
                                                                    .like(toolDynamicTabComponentVO.getContentRequired() != null,"content_required",toolDynamicTabComponentVO.getContentRequired())
                                                                    .like(StringUtils.isNotBlank(toolDynamicTabComponentVO.getContentType()),"content_type",toolDynamicTabComponentVO.getContentType())
                                                                    .like(StringUtils.isNotBlank(toolDynamicTabComponentVO.getContentItem()),"content_item",toolDynamicTabComponentVO.getContentItem())
                                        );
    }
}
