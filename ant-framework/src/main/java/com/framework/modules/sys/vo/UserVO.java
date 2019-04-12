package com.framework.modules.sys.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.framework.modules.sys.pojo.Role;
import com.framework.modules.sys.pojo.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 系统用户
 * </p>
 *
 * @author Mark
 * @since 2018-10-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 用户
     */
    private String username;

    /**
     * 用户编码
     */
    private String usercode;

    /**
     * 密码
     */
    private String password;

    /**
     * 性别
     */
    private Integer sex;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 电话
     */
    private String phone;

    /**
     * 住址
     */
    private String address;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 最近登录时间
     */
    private Timestamp lastlogin;

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

    /**
     * 当前用户所拥有的所有权限
     */

    private List<Long> roles;

    public User getPojoUser(){
        User user = new User();
        user.setId(this.id);
        user.setUsername(this.username);
        user.setUsercode(this.usercode);
        user.setSex(this.sex);
        user.setAddress(this.address);
        user.setBirthday(this.birthday);
        user.setEmail(this.email);
        user.setPhone(this.phone);
        user.setStatus(this.status);
        List<Role> rs = new ArrayList<>();
        for (Long id : this.roles) {
            Role r = new Role();
            r.setId(id);
            rs.add(r);
        }
        user.setRoles(rs);
        return user;

    }



}
