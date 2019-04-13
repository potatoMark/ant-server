package com.framework.modules.sys.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.framework.modules.sys.pojo.Menu;
import com.framework.modules.sys.pojo.Role;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
public class RoleVO implements Serializable {

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

    private List<Menu> menus;

    public Role getPojoRole(){
        Role role = new Role();
        role.setId(this.id);
        role.setNumber(this.number);
        role.setName(this.name);
        role.setMenus(this.menus);
        return role;
    }

}
