package com.framework.modules.tool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.framework.common.utils.PageUtils;
import com.framework.common.utils.RequestUtils;
import com.framework.modules.tool.dao.SchemaTableColumnDao;
import com.framework.modules.tool.pojo.SchemaTable;
import com.framework.modules.tool.pojo.SchemaTableColumn;
import com.framework.modules.tool.service.ISchemaTableColumnService;
import com.framework.modules.tool.vo.SchemaTableColumnVO;
import com.framework.modules.tool.vo.SchemaTableVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 数据系统表列 服务实现类
 * </p>
 *
 * @author Mark
 * @since 2018-10-05
 */
@Service
public class SchemaTableColumnServiceImpl extends ServiceImpl<SchemaTableColumnDao, SchemaTableColumn> implements ISchemaTableColumnService {

    @Autowired
    SchemaTableColumnDao schemaTableColumnDao;

    @Override
    public List<SchemaTableColumn> getSchemaTableColumnByCondition(SchemaTableColumnVO schemaTableColumnVO) {

        return schemaTableColumnDao.selectList(new QueryWrapper<SchemaTableColumn>().eq("table_name",schemaTableColumnVO.getTableName()));
    }

    @Override
    public PageUtils queryPage(RequestUtils params) {

        SchemaTableColumnVO schemaTableColumnVO = (SchemaTableColumnVO)params.getCondition();
        Page<SchemaTableColumn> page = (Page<SchemaTableColumn>)schemaTableColumnDao.selectPage(PageUtils.instance(params.getPage()),new QueryWrapper<SchemaTableColumn>()
                .eq("table_name",schemaTableColumnVO.getTableName()));

        return new PageUtils(page);
    }
}
