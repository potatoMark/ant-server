package com.framework.modules.tool.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.framework.common.utils.PageUtils;
import com.framework.common.utils.RequestUtils;
import com.framework.modules.tool.pojo.ToolDynamicTab;
import com.framework.modules.tool.vo.ToolDynamicTabVO;

import java.util.List;

/**
 * <p>
 * 动态标签 Service
 * </p>
 *
 * @author mark
 * @email mark.make@hotmail.com
 * @since 2019-05-08T15:12:23.900Z
 */
public interface IToolDynamicTabService extends IService<ToolDynamicTab> {

    public List<ToolDynamicTab> getToolDynamicTabs();

    PageUtils queryPage(RequestUtils params);

    public int deleteToolDynamicTabs(List<Long> toolDynamicTabIds);

    public int saveToolDynamicTab(ToolDynamicTabVO toolDynamicTabVO);

    public ToolDynamicTabVO getToolDynamicTab(Long id);

    public List<ToolDynamicTab> getToolDynamicTabsByCondition(ToolDynamicTabVO toolDynamicTabVO);
}
