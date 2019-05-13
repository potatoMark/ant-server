package com.framework.modules.tool.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import com.framework.modules.tool.pojo.ToolDynamicTabComponent;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * <p>
 * 标签构成 VO实体类
 * </p>
 *
 * @author mark
 * @email mark.make@hotmail.com
 * @since 2019-05-10T07:32:55.744Z
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ToolDynamicTabComponentVO implements Serializable {

    private static final long serialVersionUID = 1L;

        /**
     * ID
     */
    private Long id;

        /**
     * 标签构成外键ID
     */
    private Long dynamicTabId;

        /**
     * 字段编码
     */
    private String contentNumber;

        /**
     * 字段名称
     */
    private String contentName;

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
    private String contentItem;

    
    public ToolDynamicTabComponent getPojoToolDynamicTabComponent(){
            ToolDynamicTabComponent toolDynamicTabComponent = new ToolDynamicTabComponent();

                        toolDynamicTabComponent.setId(this.id);
                        toolDynamicTabComponent.setDynamicTabId(this.dynamicTabId);
                        toolDynamicTabComponent.setContentNumber(this.contentNumber);
                        toolDynamicTabComponent.setContentName(this.contentName);
                        toolDynamicTabComponent.setContentRequired(this.contentRequired);
                        toolDynamicTabComponent.setContentType(this.contentType);
                        toolDynamicTabComponent.setContentItem(this.contentItem);
                        return toolDynamicTabComponent;

        }



}
