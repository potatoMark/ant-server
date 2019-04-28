package com.framework.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.Condition;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.framework.common.utils.PageUtils;
import com.framework.common.utils.PojoUtils;
import com.framework.common.utils.RequestUtils;
import com.framework.modules.sys.dao.UserDao;
import com.framework.modules.sys.dao.UserRoleDao;
import com.framework.modules.sys.pojo.Role;
import com.framework.modules.sys.pojo.User;
import com.framework.modules.sys.pojo.UserRole;
import com.framework.modules.sys.service.IUserService;
import com.framework.modules.sys.vo.UserVO;
import net.sf.jsqlparser.expression.StringValue;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author Mark
 * @since 2018-10-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService, UserDetailsService {

    @Autowired
    UserDao userDao;

    @Autowired
    UserRoleDao userRoleDao;

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value="redisCache", key = "'redis_user_'+#result.id")
    public User insertUser(User user) {

         userDao.insert(user);
        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CachePut(value="redisCache", key = "'redis_user_' + #user.id",
        condition = "#result != 'null'")
    public User updateUser(User user) {

        User result = getUser(user.getId());

        if (result == null) {
            return null;
        }
        userDao.updateById(user);

        return user;
    }

    @Override
    @Cacheable(value="redisCache", key = "'redis_user_'+#id")
    public User getUser(Long id) {
        return userDao.loadUserById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value="redisCache", key = "'redis_user_'+#id",
        beforeInvocation = false)
    public int deleteUser(Long id) {
        return userDao.deleteById(Long.valueOf(id));
    }

    @Override
    public UserDetails loadUserByUsername(String usercode) throws UsernameNotFoundException {
        User user = userDao.loadUserByUsercode(usercode);
        return user == null? new User():user;
    }

    @Override
    public List<User> getUsers() {
        return userDao.loadUsers();
    }

    @Override
    public PageUtils queryPage(RequestUtils params) {

        UserVO userVO = (UserVO)params.getCondition();
        Page<User> page = (Page<User>)userDao.selectPage(PageUtils.instance(params.getPage()),new QueryWrapper<User>()
                .like(StringUtils.isNotBlank(userVO.getUsername()),"username",userVO.getUsername())
                .like(StringUtils.isNotBlank(userVO.getUsercode()),"usercode",userVO.getUsercode())
                .eq(userVO.getSex() !=null,"sex",userVO.getSex())
                .like(StringUtils.isNotBlank(userVO.getPhone()),"phone",userVO.getPhone())
                .eq(userVO.getStatus() !=null,"status",userVO.getStatus())
        );

//        Page<User> page = (Page<User>)userDao.loadUserPage(PageUtils.instance(params.getPage()), (UserVO)params.getCondition());
        return new PageUtils(page);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteUsers(List<Integer> userIds) {
        for (Integer userId : userIds) {
            userRoleDao.delete(new QueryWrapper<UserRole>().eq("user_id",userId));
        }
        return userDao.deleteBatchIds(userIds);
    }

    @Override
    public User getUserByUserCode(String usercode) {
        return userDao.loadUserByUsercode(usercode);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int saveUser(User user) {
        PojoUtils.changeDate(user);

        if (user.getId() != null) {
            userRoleDao.delete(new QueryWrapper<UserRole>().eq("user_id",user.getId()));
            int rst = userDao.updateById(user);
            for (Role role : user.getRoles()) {

                UserRole userRole = new UserRole();
                userRole.setUserId(user.getId());
                userRole.setRoleId(role.getId());

                userRoleDao.insert(userRole);
            }
            return rst;
        } else {

            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            user.setPassword(encoder.encode("123456"));
            int rst = userDao.insert(user);
            for (Role role : user.getRoles()) {

                UserRole userRole = new UserRole();
                userRole.setUserId(user.getId());
                userRole.setRoleId(role.getId());

                userRoleDao.insert(userRole);
            }
            return rst;
        }
    }

    @Override
    public List<User> getUsersByCondition(UserVO userVO) {

        return userDao.selectList(new QueryWrapper<User>()
                .like(StringUtils.isNotBlank(userVO.getUsername()),"username",userVO.getUsername())
                .like(StringUtils.isNotBlank(userVO.getUsercode()),"usercode",userVO.getUsercode())
                .like(userVO.getSex() !=null,"sex",userVO.getSex())
                .like(StringUtils.isNotBlank(userVO.getPhone()),"phone",userVO.getPhone())
                .like(userVO.getStatus() !=null,"status",userVO.getStatus())
        );
    }
}
