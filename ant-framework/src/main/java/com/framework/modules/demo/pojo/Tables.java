package com.framework.modules.demo.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Mark
 * @since 2018-11-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("demo_tables")
public class Tables implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String email;

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


}
