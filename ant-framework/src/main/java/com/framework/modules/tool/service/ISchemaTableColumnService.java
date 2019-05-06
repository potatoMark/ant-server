package com.framework.modules.tool.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.framework.common.utils.PageUtils;
import com.framework.common.utils.RequestUtils;
import com.framework.modules.tool.pojo.SchemaTableColumn;
import com.framework.modules.tool.vo.SchemaTableColumnVO;

import java.util.List;

/**
 * <p>
 * 数据表列 服务类
 * </p>
 *
 * @author Mark
 * @since 2019-10-05
 */
public interface ISchemaTableColumnService extends IService<SchemaTableColumn> {



    List<SchemaTableColumn> getSchemaTableColumnByCondition(SchemaTableColumnVO schemaTableColumnVO);

    PageUtils queryPage(RequestUtils params);

}
