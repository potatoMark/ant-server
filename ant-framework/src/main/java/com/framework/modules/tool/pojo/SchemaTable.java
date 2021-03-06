package com.framework.modules.tool.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.sql.Timestamp;

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
@TableName("v_schematable")
public class SchemaTable implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tableName;

    private String engine;

    private String tableComment;

    private Timestamp createTime;

    private String tableSchema;



}
