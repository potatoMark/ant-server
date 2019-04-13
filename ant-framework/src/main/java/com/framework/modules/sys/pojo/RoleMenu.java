package com.framework.modules.sys.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("jointable_role_menu")
public class RoleMenu implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableField(value = "menu_id")
    private Long menuId;

    @TableField(value = "role_id")
    private Long roleId;
}
