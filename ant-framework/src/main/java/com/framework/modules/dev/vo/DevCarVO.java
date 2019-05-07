package com.framework.modules.dev.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import com.framework.modules.dev.pojo.DevCar;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * <p>
 * 汽车 VO实体类
 * </p>
 *
 * @author mark
 * @email mark.make@hotmail.com
 * @since 2019-05-07T07:32:19.931Z
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DevCarVO implements Serializable {

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
     * 状态
     */
    private Long status;

        /**
     * 维修时间
     */
    private Date repairtime;

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

    
    public DevCar getPojoDevCar(){
            DevCar devCar = new DevCar();

                        devCar.setId(this.id);
                        devCar.setNumber(this.number);
                        devCar.setName(this.name);
                        devCar.setStatus(this.status);
                        devCar.setRepairtime(this.repairtime);
                        devCar.setCreateuser(this.createuser);
                        devCar.setUpdateuser(this.updateuser);
                        devCar.setCreatedate(this.createdate);
                        devCar.setUpdatedate(this.updatedate);
                        return devCar;

        }



}
