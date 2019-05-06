package com.framework.modules.tool.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.framework.common.utils.PageUtils;
import com.framework.common.utils.RequestUtils;
import com.framework.modules.tool.pojo.SchemaTable;
import com.framework.modules.tool.vo.SchemaTableVO;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * <p>
 * 快速生成 Service
 * </p>
 *
 * @author Mark
 * @email mark.make@hotmail.com
 * @since 2019-05-02
 */
public interface ISchemaTableService extends IService<SchemaTable> {

    public List<SchemaTable> getSchemaTables();

    PageUtils queryPage(RequestUtils params);

    public int deleteSchemaTables(List<String> schemaTableTableNames);

    public int saveSchemaTable(SchemaTable schemaTable);

    public SchemaTable getSchemaTable(String tableName);

    public List<SchemaTable> getSchemaTablesByCondition(SchemaTableVO schemaTableVO);

    public byte[] generateCode(SchemaTableVO schemaTableVO) throws Exception;
}
