package com.framework;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.framework.common.utils.DateUtils;
import com.framework.common.utils.GenerateUtils;
import com.framework.modules.tool.dao.SchemaTableColumnDao;
import com.framework.modules.tool.dao.SchemaTableDao;
import com.framework.modules.tool.pojo.SchemaTable;
import com.framework.modules.tool.pojo.SchemaTableColumn;
import com.framework.modules.tool.vo.SchemaTableColumnVO;
import com.framework.modules.tool.vo.SchemaTableVO;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipOutputStream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class velocityTests {

    protected Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private SchemaTableColumnDao schemaTableColumnDao;

    @Autowired
    private SchemaTableDao schemaTableDao;


    @Test
    public void testSelect() throws Exception{
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);

        SchemaTableVO schemaTableVO = new SchemaTableVO();

        schemaTableVO = testData(schemaTableVO);
        GenerateUtils.generatorCode(schemaTableVO, zip);
        IOUtils.closeQuietly(zip);

        String filePath = "D://zbc.zip";
//创建文件
        File file = new File(filePath);
        try{
            byte[] bs = outputStream.toByteArray();
            FileOutputStream fileOutputStream = new FileOutputStream(file);

            fileOutputStream.write(bs);
        }catch(Exception e){
            System.out.println("新建文件出错");
            //e.printStackTrace();
        }finally{
        }



        List<SchemaTable> schemaTableList =  schemaTableDao.selectList(new QueryWrapper<SchemaTable>().eq("table_name","sys_user"));
//
//        List<SchemaTableColumn>  schemaTableColumns = schemaTableColumnDao.selectList(new QueryWrapper<SchemaTableColumn>().eq("table_name","sys_user"));
//
//
//        logger.info(schemaTableList.size()+"");

    }

    public SchemaTableVO testData(SchemaTableVO schemaTableVO){
        String table="v_schematable";
        SchemaTable schemaTable =  schemaTableDao.selectOne(new QueryWrapper<SchemaTable>().eq("table_name",table));

        List<SchemaTableColumn> schemaTableColumns =  schemaTableColumnDao.selectList(new QueryWrapper<SchemaTableColumn>().eq("table_name",table));

        schemaTableVO = new SchemaTableVO();

        schemaTableVO.setTableName("v_schema_table");
        schemaTableVO.setTableComment("快速生成");
        schemaTableVO.setModuleName("tool");
        schemaTableVO.setAuthor("Mark");
        schemaTableVO.setEmail("mark.make@hotmail.com");
        schemaTableVO.setCreateDate(DateUtils.format(new Date()));

        List<SchemaTableColumnVO> schemaTableColumnVOS = new ArrayList<>();

        schemaTableColumns.forEach(item->{
            SchemaTableColumnVO schemaTableColumnVO = new SchemaTableColumnVO();
            schemaTableColumnVO.setColumnName(item.getColumnName());
            schemaTableColumnVO.setDataType("varchar");
            schemaTableColumnVO.setColumnComment("列1");

            schemaTableColumnVO.setShowListModel("show");
            schemaTableColumnVO.setShowEdit(true);
            schemaTableColumnVO.setShowEditModel("show");
            schemaTableColumnVO.setEditComponent("input");
            schemaTableColumnVO.setCondition(true);
            schemaTableColumnVOS.add(schemaTableColumnVO);
        });

        schemaTableVO.setSchemaTableColumnVOList(schemaTableColumnVOS);
        return schemaTableVO;
    }
}
