package com.framework.modules.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.framework.modules.sys.pojo.User;
import com.framework.modules.sys.pojo.UserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统用户 Mapper 接口
 * </p>
 *
 * @author Mark
 * @since 2018-10-02
 */
public interface UserRoleDao extends BaseMapper<UserRole> {

}
