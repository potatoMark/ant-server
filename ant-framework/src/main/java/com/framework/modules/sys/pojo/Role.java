package com.framework.modules.sys.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 系统角色
 * </p>
 *
 * @author Mark
 * @since 2018-10-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色编码
     */
    private String number;

    /**
     * 角色名称
     */
    private String name;

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
    private LocalDateTime createdate;

    /**
     * 修改日期
     */
    private LocalDateTime updatedate;

    @TableField(exist = false)
    private List<Menu> menus;

}
