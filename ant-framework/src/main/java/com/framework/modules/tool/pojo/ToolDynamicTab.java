package com.framework.modules.tool.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * <p>
 * 动态标签 POJO实体类
 * </p>
 *
 * @author mark
 * @email mark.make@hotmail.com
 * @since 2019-05-08T15:12:23.900Z
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tool_dynamic_tab")
public class ToolDynamicTab implements Serializable {

    private static final long serialVersionUID = 1L;

        /**
     * ID
     */
        @TableId(value = "id", type = IdType.AUTO)
        private Long id;
        /**
     * 表名
     */
        @TableField(value="table_name")
        private String tableName;
        /**
     * 标签编码
     */
        @TableField(value="tab_table_number")
        private String tabTableNumber;
        /**
     * 标签名称
     */
        @TableField(value="tab_table_name")
        private String tabTableName;
        /**
     * 激活
     */
        @TableField(value="active")
        private Integer active;
        /**
     * 创建者
     */
        @TableField(value="createuser")
        private String createuser;
        /**
     * 修改者
     */
        @TableField(value="updateuser")
        private String updateuser;
        /**
     * 创建日期
     */
        @TableField(value="createdate")
        private Timestamp createdate;
        /**
     * 修改日期
     */
        @TableField(value="updatedate")
        private Timestamp updatedate;
    


}
