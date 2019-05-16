package com.framework.common.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DynamicDdl {

    private String tableName;

    private String fkName;

    private Long id;

    private String updateContentSQL;


}
