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
 * 标签构成 POJO实体类
 * </p>
 *
 * @author mark
 * @email mark.make@hotmail.com
 * @since 2019-05-10T07:32:55.744Z
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tool_dynamic_tab_component")
public class ToolDynamicTabComponent implements Serializable {

    private static final long serialVersionUID = 1L;

        /**
     * ID
     */
        @TableId(value = "id", type = IdType.AUTO)
        private Long id;
        /**
     * 标签构成外键ID
     */
        @TableField(value="dynamic_tab_id")
        private Long dynamicTabId;
        /**
     * 字段编码
     */
        @TableField(value="content_number")
        private String contentNumber;
        /**
     * 字段名称
     */
        @TableField(value="content_name")
        private String contentName;
        /**
     * 必须
     */
        @TableField(value="content_required")
        private Integer contentRequired;
        /**
     * 类型
     */
        @TableField(value="content_type")
        private String contentType;
        /**
     * 选择项
     */
        @TableField(value="content_item")
        private String contentItem;

    @TableField(exist = false)
    private Object contentValue;

}
