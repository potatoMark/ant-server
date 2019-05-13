package com.framework.modules.tool.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.framework.common.utils.PageUtils;
import com.framework.common.utils.RequestUtils;
import com.framework.modules.tool.pojo.ToolDynamicTabComponent;
import com.framework.modules.tool.vo.ToolDynamicTabComponentVO;

import java.util.List;

/**
 * <p>
 * 标签构成 Service
 * </p>
 *
 * @author mark
 * @email mark.make@hotmail.com
 * @since 2019-05-10T07:32:55.744Z
 */
public interface IToolDynamicTabComponentService extends IService<ToolDynamicTabComponent> {

    public List<ToolDynamicTabComponent> getToolDynamicTabComponents();

    PageUtils queryPage(RequestUtils params);

    public int deleteToolDynamicTabComponents(List<Long> toolDynamicTabComponentIds);

    public int saveToolDynamicTabComponent(ToolDynamicTabComponent toolDynamicTabComponent);

    public ToolDynamicTabComponent getToolDynamicTabComponent(Long id);

    public List<ToolDynamicTabComponent> getToolDynamicTabComponentsByCondition(ToolDynamicTabComponentVO toolDynamicTabComponentVO);
}
