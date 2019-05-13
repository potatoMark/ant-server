package com.framework.modules.tool.vo;

import com.framework.modules.tool.pojo.SchemaTable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * <p>
 * 数据库表 实体类
 * </p>
 *
 * @author Mark
 * @since 2018-10-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SchemaTableVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tableName;

    private String engine;

    private String tableComment;

    private Timestamp createTime;

    private String tableSchema;

    private String className;

    private String classname;

    private String moduleName;

    private String packageName;

    private String author;

    private String email;

    private String createDate;

    private List<SchemaTableColumnVO> schemaTableColumnVOList;

    public String getClassName(){

        if (StringUtils.isNotBlank(this.tableName)) {
            String[] strs = this.tableName.split("_");

            int loop = 0;
            if (strs.length == 1) {
                return strs[0].substring(0,1).toUpperCase().concat(strs[0].substring(1).toLowerCase());
            }
            if ("v".equals(strs[0])) {
                loop = 1;
            }

            String content = "";
            for ( ; loop<strs.length; loop++ ) {

                content = content.concat(strs[loop].substring(0,1).toUpperCase().concat(strs[loop].substring(1).toLowerCase()));

            }
            return content;
        }
        return "";
    }

    public String getClassname(){

        if (StringUtils.isNotBlank(this.tableName)) {
            String[] strs = this.tableName.split("_");

            int loop = 0;
            if (strs.length == 1) {
                return strs[0];
            }
            if ("v".equals(strs[0])) {
                loop = 1;
            }

            String content = "";
            boolean flg = true;
            for ( ; loop<strs.length; loop++ ) {

                if (flg){

                    content = strs[loop].toLowerCase();
                    flg=false;
                } else {

                    content = content.concat(strs[loop].substring(0,1).toUpperCase().concat(strs[loop].substring(1).toLowerCase()));
                }

            }
            return content;
        }
        return "";
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public SchemaTable getPojoSchemaTable(){
        SchemaTable schemaTable = new SchemaTable();

        schemaTable.setTableName(this.tableName);
        schemaTable.setEngine(this.engine);
        schemaTable.setTableComment(this.tableComment);
        schemaTable.setCreateTime(this.createTime);
        schemaTable.setTableSchema(this.tableSchema);
        return schemaTable;

    }
}
