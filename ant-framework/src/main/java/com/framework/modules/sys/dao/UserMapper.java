package com.framework.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.framework.modules.sys.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 系统用户 Mapper 接口
 * </p>
 *
 * @author Mark
 * @since 2018-10-02
 */
public interface UserMapper extends BaseMapper<User> {
    User loadUserByUsercode(String usercode);

    User loadUserById(Long id);

    List<User> loadUsers();

    IPage<User> loadUserPage(@Param("pg") Page<User> page, @Param("user") User user);
}
