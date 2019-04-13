package com.framework.modules.sys.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.sound.midi.MetaEventListener;

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
@TableName("sys_menu")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

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
    @TableField("parent_id")
    private Integer parentId;

    /**
     * 排序
     */
    private Integer sort;


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
    private List<Menu> children;



}
