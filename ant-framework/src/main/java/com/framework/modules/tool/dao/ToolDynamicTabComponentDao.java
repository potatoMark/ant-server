package com.framework.modules.tool.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.framework.modules.tool.pojo.ToolDynamicTabComponent;

import java.util.List;

/**
 * <p>
 * 标签构成 DAO类
 * </p>
 *
 * @author mark
 * @email mark.make@hotmail.com
 * @since 2019-05-10T07:32:55.744Z
 */
public interface ToolDynamicTabComponentDao extends BaseMapper<ToolDynamicTabComponent> {

    List<ToolDynamicTabComponent> queryByDynamicTabId(Long id);
}
