package com.framework.common.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 动态TAB对应内容的VO处理类
 * </p>
 *
 * @author Mark
 * @since 2018-10-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DynamicTabContentVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long contentId;

    /**
     * 字段编码
     */
    private String contentNumber;

    /**
     * 字段名称
     */
    private String contentName;

    /**
     * 字段值
     */
    private Object contentValue;

    /**
     * 必须
     */
    private Integer contentRequired;

    /**
     * 类型
     */
    private String contentType;

    /**
     * 选择项
     */
    private String[] contentItems;


}
