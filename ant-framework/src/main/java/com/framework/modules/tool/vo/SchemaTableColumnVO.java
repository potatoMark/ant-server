package com.framework.modules.tool.vo;

import com.framework.modules.tool.pojo.SchemaTableColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 数据库表对应列 实体类
 * </p>
 *
 * @author Mark
 * @since 2018-10-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SchemaTableColumnVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tableName;

    private String columnName;

    private String dataType;

    private String columnComment;

    private String columnKey;

    private String extra;

    private boolean condition;

    private boolean required ;

    private String attrName;

    private String attrname;

    private String showListModel;// show/hidden

    private boolean showEdit;

    private String showEditModel;// show/hidden/disabed

    private String editComponent;// input/date/datetime/checkbox/select

    private String selectModel;//remote/local

    private String selectLocalItem;//only local

    private String selectRemotePojo;//only remote

//    private String editDefaultValue;

    private List<String> options;


    public String getAttrName() {

        if (StringUtils.isNotBlank(columnName)) {
            String[] strs = this.columnName.split("_");

            if (strs.length == 1) {
                return strs[0].substring(0,1).toUpperCase().concat(strs[0].substring(1).toLowerCase());
            }

            String content = "";
            for (int loop = 0; loop<strs.length; loop++ ) {

                content = content.concat(strs[loop].substring(0,1).toUpperCase().concat(strs[loop].substring(1).toLowerCase()));

            }
            return content;
        }
        return "";
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName;
    }

    public String getAttrname() {

        if (StringUtils.isNotBlank(columnName)) {
            String[] strs = this.columnName.split("_");

            if (strs.length == 1) {
                return strs[0].toLowerCase();
            }

            String content = "";
            boolean flg = true;
            for (int loop = 0; loop<strs.length; loop++ ) {

                if(flg) {
                    content = content.concat(strs[loop].toLowerCase());
                    flg = false;
                } else {

                    content = content.concat(strs[loop].substring(0,1).toUpperCase().concat(strs[loop].substring(1).toLowerCase()));
                }

            }
            return content;
        }
        return "";

    }

    public void setAttrname(String attrname) {
        this.attrname = attrname;
    }

    public void setPojoToVo(SchemaTableColumn schemaTableColumn){
        if ("id".equals(schemaTableColumn.getColumnName())
            ||"createuser".equals(schemaTableColumn.getColumnName())
            ||"updateuser".equals(schemaTableColumn.getColumnName())
            ||"createdate".equals(schemaTableColumn.getColumnName())
            ||"updatedate".equals(schemaTableColumn.getColumnName())) {
            this.condition = false;
            this.showListModel="hidden";
            this.required=false;
        } else {
            this.condition = true;
            this.showListModel="show";
            this.required=true;
        }

        if ("createdate".equals(schemaTableColumn.getColumnName())
                ||"updatedate".equals(schemaTableColumn.getColumnName())) {
            this.editComponent="datetime";
        } else {
            this.editComponent="input";
        }

        if ("id".equals(schemaTableColumn.getColumnName())) {
            this.showEditModel="hidden";
        } else {
            this.showEditModel="show";
        }
        this.tableName = schemaTableColumn.getTableName();
        this.columnName = schemaTableColumn.getColumnName();
        this.dataType = schemaTableColumn.getDataType();
        this.columnComment = schemaTableColumn.getColumnComment();
        this.columnKey = schemaTableColumn.getColumnKey();
        this.showEdit=true;

        this.selectLocalItem="";

    }

}
