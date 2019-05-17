package com.framework.modules.dev.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import com.framework.modules.dev.pojo.Bus;
import com.framework.common.entity.DynamicTabVO;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 公车 VO实体类
 * </p>
 *
 * @author mark
 * @email mark.make@hotmail.com
 * @since 2019-05-17T02:00:23.993Z
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class BusVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 编码
     */
    private String number;

    /**
     * 名称
     */
    private String name;

    /**
     * 时间
     */
    private Timestamp testtime;

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


    private List<DynamicTabVO> dynamicTabs;

    public Bus getPojoBus(){
        Bus bus = new Bus();

        bus.setId(this.id);
        bus.setNumber(this.number);
        bus.setName(this.name);
        bus.setTesttime(this.testtime);
        bus.setCreateuser(this.createuser);
        bus.setUpdateuser(this.updateuser);
        bus.setCreatedate(this.createdate);
        bus.setUpdatedate(this.updatedate);
        bus.setDynamicTabs(this.dynamicTabs);
        return bus;

   }



}
