package com.framework.modules.dev.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import com.framework.common.entity.DynamicTabVO;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 公车 POJO实体类
 * </p>
 *
 * @author mark
 * @email mark.make@hotmail.com
 * @since 2019-05-16T15:40:35.080Z
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("dev_bus")
public class Bus implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 编码
     */
    @TableField(value="number")
    private String number;
    /**
     * 名称
     */
    @TableField(value="name")
    private String name;
    /**
     * 时间
     */
    @TableField(value="testTime")
    private Timestamp testtime;
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

    @TableField(exist = false)
    private List<DynamicTabVO> dynamicTabs;



}
