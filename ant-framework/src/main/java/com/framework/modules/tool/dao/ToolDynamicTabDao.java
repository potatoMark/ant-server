package com.framework.modules.tool.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.framework.common.entity.DynamicDdl;
import com.framework.modules.tool.pojo.ToolDynamicTab;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 动态标签 DAO类
 * </p>
 *
 * @author mark
 * @email mark.make@hotmail.com
 * @since 2019-05-08T15:12:23.900Z
 */
public interface ToolDynamicTabDao extends BaseMapper<ToolDynamicTab> {

    List<ToolDynamicTab> queryListByTableName(String tableName);

    Map<String,Object> queryDynamicByFK(String tableName, String fkName, Long id);

    int dmlDynamicTable(@Param("dynamicSql")String dynamicSql);

}
