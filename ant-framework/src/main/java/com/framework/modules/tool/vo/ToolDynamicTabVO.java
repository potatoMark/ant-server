package com.framework.modules.tool.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import com.framework.modules.tool.pojo.ToolDynamicTab;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 动态标签 VO实体类
 * </p>
 *
 * @author mark
 * @email mark.make@hotmail.com
 * @since 2019-05-08T15:12:23.900Z
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ToolDynamicTabVO implements Serializable {

    private static final long serialVersionUID = 1L;

        /**
     * ID
     */
    private Long id;

        /**
     * 表名
     */
    private String tableName;

        /**
     * 标签编码
     */
    private String tabTableNumber;

        /**
     * 标签名称
     */
    private String tabTableName;

        /**
     * 激活
     */
    private Integer active;

        /**
     * 创建者
     */
    private String createuser;

        /**
     * 修改者
     */
    private String updateuser;

        /**
     * 创建日期
     */
    private Timestamp createdate;

        /**
     * 修改日期
     */
    private Timestamp updatedate;

    private List<ToolDynamicTabComponentVO> toolDynamicTabComponentVOS;

    
    public ToolDynamicTab getPojoToolDynamicTab(){
            ToolDynamicTab toolDynamicTab = new ToolDynamicTab();

                        toolDynamicTab.setId(this.id);
                        toolDynamicTab.setTableName(this.tableName);
                        toolDynamicTab.setTabTableNumber(this.tabTableNumber);
                        toolDynamicTab.setTabTableName(this.tabTableName);
                        toolDynamicTab.setActive(this.active);
                        toolDynamicTab.setCreateuser(this.createuser);
                        toolDynamicTab.setUpdateuser(this.updateuser);
                        toolDynamicTab.setCreatedate(this.createdate);
                        toolDynamicTab.setUpdatedate(this.updatedate);
                        return toolDynamicTab;

        }



}
