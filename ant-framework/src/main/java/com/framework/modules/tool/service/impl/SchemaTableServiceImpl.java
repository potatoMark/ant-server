package com.framework.modules.tool.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.framework.common.utils.GenerateUtils;
import com.framework.common.utils.PageUtils;
import com.framework.common.utils.PojoUtils;
import com.framework.common.utils.RequestUtils;
import com.framework.modules.tool.pojo.SchemaTable;
import com.framework.modules.tool.vo.SchemaTableVO;
import com.framework.modules.tool.service.ISchemaTableService;
import com.framework.modules.tool.dao.SchemaTableDao;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.zip.ZipOutputStream;

/**
 * <p>
 * 快速生成 服务实现类
 * </p>
 *
 * @author Mark
 * @email mark.make@hotmail.com
 * @since 2019-05-02
 */
@Service
public class SchemaTableServiceImpl extends com.baomidou.mybatisplus.extension.service.impl.ServiceImpl<SchemaTableDao, SchemaTable> implements ISchemaTableService {

    @Autowired
    SchemaTableDao schemaTableDao;

    @Override
    public List<SchemaTable> getSchemaTables() {
        return schemaTableDao.selectList(null);
    }

    @Override
    public PageUtils queryPage(RequestUtils params) {
        SchemaTableVO schemaTableVO = (SchemaTableVO)params.getCondition();
        Page<SchemaTable> page = (Page<SchemaTable>)schemaTableDao.selectPage(PageUtils.instance(params.getPage()),new QueryWrapper<SchemaTable>()
                                                                                        .like(StringUtils.isNotBlank(schemaTableVO.getTableName()),"table_name",schemaTableVO.getTableName())
                                                    );
        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteSchemaTables(List<String> schemaTableTableNames) {


                    schemaTableTableNames.forEach(item->{
                schemaTableDao.delete(new QueryWrapper<SchemaTable>().eq("TABLE_NAME",item));
            });
            return 0;
        

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveSchemaTable(SchemaTable schemaTable) {
        PojoUtils.changeDate(schemaTable);
                    int rst = schemaTableDao.insert(schemaTable);
            return rst;
        
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SchemaTable getSchemaTable(String tableName) {

                    return schemaTableDao.selectOne(new QueryWrapper<SchemaTable>().eq("TABLE_NAME",tableName));
        

    }

    @Override
    public List<SchemaTable> getSchemaTablesByCondition(SchemaTableVO schemaTableVO) {

        return schemaTableDao.selectList(new QueryWrapper<SchemaTable>()
                                                                .eq(StringUtils.isNotBlank(schemaTableVO.getTableName()),"table_name",schemaTableVO.getTableName())
                                        );
    }

    @Override
    public byte[] generateCode(SchemaTableVO schemaTableVO) throws Exception {

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        GenerateUtils.generatorCode(schemaTableVO, zip);
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }
}
