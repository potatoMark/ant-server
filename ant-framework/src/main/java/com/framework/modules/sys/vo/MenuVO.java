package com.framework.modules.sys.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.framework.modules.sys.pojo.Menu;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 系统菜单
 * </p>
 *
 * @author Mark
 * @since 2018-10-10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class MenuVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * PATH
     */
    private String path;


    /**
     * 名称
     */
    private String name;


    /**
     * 父节点
     */
    private Integer parentId;

    /**
     * 排序
     */
    private Integer sort;

    public Menu getPojoMenu(){
        Menu menu = new Menu();
        menu.setId(this.id);
        menu.setName(this.name);
        menu.setPath(this.path);
        menu.setSort(this.sort);
        menu.setParentId(this.parentId);
        return menu;
    }

}
