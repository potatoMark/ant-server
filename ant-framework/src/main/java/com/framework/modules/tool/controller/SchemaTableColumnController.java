package com.framework.modules.tool.controller;


import com.framework.common.utils.PageUtils;
import com.framework.common.utils.R;
import com.framework.common.utils.RequestUtils;
import com.framework.modules.tool.pojo.SchemaTableColumn;
import com.framework.modules.tool.service.ISchemaTableColumnService;
import com.framework.modules.tool.service.ISchemaTableService;
import com.framework.modules.tool.vo.SchemaTableColumnVO;
import com.framework.modules.tool.vo.SchemaTableVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 数据库表列 前端控制器
 * </p>
 *
 * @author Mark
 * @since 2018-10-05
 */
@RestController
@RequestMapping("/tool")
public class SchemaTableColumnController {

    @Autowired
    ISchemaTableColumnService schemaTableColumnService;

    @PostMapping("/schemaTableColumns/getSchemaTableColumnsByCondition")
    public R getSchemaTableColumnsByCondition(@RequestBody SchemaTableColumnVO schemaTableColumnVO){
        List<SchemaTableColumn> schemaTableColumns = schemaTableColumnService.getSchemaTableColumnByCondition(schemaTableColumnVO);
        List<SchemaTableColumnVO> schemaTableColumnVOS = new ArrayList<>();

        schemaTableColumns.forEach(item->{
            SchemaTableColumnVO schemaTableColumnVO1 = new SchemaTableColumnVO();
            schemaTableColumnVO1.setPojoToVo(item);
            schemaTableColumnVOS.add(schemaTableColumnVO1);
        });
        return R.ok().putResult(schemaTableColumnVOS);
    }

    @PostMapping("/schemaTableColumns/page")
    public R getSchemaTableColumnList(@RequestBody RequestUtils<SchemaTableColumnVO> params){
        PageUtils page = schemaTableColumnService.queryPage(params);
        return R.ok().putResult(page);
    }

}
