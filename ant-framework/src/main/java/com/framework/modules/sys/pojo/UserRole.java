package com.framework.modules.sys.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("jointable_user_role")
public class UserRole implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableField(value = "user_id")
    private Long userId;

    @TableField(value = "role_id")
    private Long roleId;
}
