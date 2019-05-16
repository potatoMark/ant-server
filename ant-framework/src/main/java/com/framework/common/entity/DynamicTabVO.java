package com.framework.common.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 动态TAB的VO处理类
 * </p>
 *
 * @author Mark
 * @since 2018-10-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DynamicTabVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String label; // Tab显示的名称,对应tabTableName

    private String name; //Tab对应的name属性,对应tabTableNumber

    private Long tabId;

    private List<DynamicTabContentVO> contents;//Tab对应的内容信息

}
