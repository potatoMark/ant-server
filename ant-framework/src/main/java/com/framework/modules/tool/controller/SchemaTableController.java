package com.framework.modules.tool.controller;


import com.framework.common.utils.PageUtils;
import com.framework.common.utils.R;
import com.framework.common.utils.RequestUtils;
import com.framework.modules.tool.pojo.SchemaTable;
import com.framework.modules.tool.vo.SchemaTableVO;
import com.framework.modules.tool.service.ISchemaTableService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

/**
 * <p>
 * 快速生成 前端控制器
 * </p>
 *
 * @author Mark
 * @email mark.make@hotmail.com
 * @since 2019-05-02
 */
@RestController
@RequestMapping("/tool")
public class SchemaTableController {

    @Autowired
    ISchemaTableService schemaTableService;

    @GetMapping("/schemaTables")
    public R getSchemaTables(){

        return R.ok().putResult(schemaTableService.getSchemaTables());
    }

    @PostMapping("/schemaTables/page")
    public R getSchemaTableList(@RequestBody RequestUtils<SchemaTableVO> params){
        PageUtils page = schemaTableService.queryPage(params);
        return R.ok().putResult(page);
    }

    @DeleteMapping("/schemaTables/delete")
    public R deleteSchemaTable(@RequestParam List<String> schemaTableTableNames){
        int rst = schemaTableService.deleteSchemaTables(schemaTableTableNames);
        return R.ok().putResult(rst);
    }

    @PostMapping("/schemaTables/save")
    public R saveSchemaTable(@RequestBody SchemaTableVO schemaTableVO){
        SchemaTable schemaTable = schemaTableVO.getPojoSchemaTable();
        int rst = schemaTableService.saveSchemaTable(schemaTable);
        return R.ok();
    }

    @GetMapping("/schemaTables/{tableName}")
    public R getSchemaTable(@PathVariable(name="tableName", required = true) String tableName){

        SchemaTable schemaTable = schemaTableService.getSchemaTable(tableName);

        return R.ok().putResult(schemaTable);
    }


    @PostMapping(value = "/schemaTables/getSchemaTablesByCondition")
    public R getSchemaTablesByCondition(@RequestBody SchemaTableVO schemaTableVO) throws Exception {

        List<SchemaTable> schemaTables = schemaTableService.getSchemaTablesByCondition(schemaTableVO);

        return R.ok().putResult(schemaTables);
    }

    @PostMapping(value = "/schemaTables/generateCode")
    public void generateCode(@RequestBody SchemaTableVO schemaTableVO, HttpServletResponse response) throws Exception {


        byte[] bytes = schemaTableService.generateCode(schemaTableVO);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"ant-framework.zip\"");
        response.addHeader("Content-Length", "" + bytes.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(bytes, response.getOutputStream());
    }
}
