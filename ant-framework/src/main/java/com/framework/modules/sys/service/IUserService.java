package com.framework.modules.sys.service;

import com.framework.common.utils.PageUtils;
import com.framework.common.utils.RequestUtils;
import com.framework.modules.sys.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.framework.modules.sys.vo.UserVO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author Mark
 * @since 2018-10-02
 */
public interface IUserService extends IService<User> {

    public User insertUser(User user);

    public User updateUser(User user);

    public User getUser(Long id);

    public List<User> getUsers();

    public int deleteUser(Long id);

    PageUtils queryPage(RequestUtils params);

    public int deleteUsers(List<Integer> userIds);

    public User getUserByUserCode(String usercode);

    public int saveUser(User user);

    public List<User> getUsersByCondition(UserVO userVO);
}
