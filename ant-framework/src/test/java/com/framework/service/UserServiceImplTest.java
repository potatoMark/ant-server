package com.framework.service;

import com.framework.common.datasources.DataSourceNames;
import com.framework.common.datasources.annotation.DataSource;
import com.framework.modules.sys.pojo.User;
import com.framework.modules.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author Mark
 * @since 2018-10-02
 */
@Service
public class UserServiceImplTest {
    @Autowired
    private IUserService userService;

    public User queryUser(Long userId) {
        return userService.getById(1);
    }

    @DataSource(name = DataSourceNames._SLAVE_)
    public User queryUser2(Long userId) {
        return userService.getById(2);
    }

    @DataSource(name = DataSourceNames._SLAVE_)
    @Transactional(rollbackFor = Exception.class)
    public int insertUser() {

        User user = new User();

        user.setUsername("x");
        user.setUsercode("001");
        user.setPassword("12");
        user.setSex(1);
        User user1 = new User();
        user1.setUsername("x11111111111111111111111111111111111111111111111111111111111111111111111111111");
        user1.setUsercode("001");
        user1.setPassword("12");
        user1.setSex(1);

        userService.save(user);
        try{
            int s = 1/0;
        }catch (Exception e) {
            throw new RuntimeException();
        }
//        userService.save(user1);
        return 0;
    }
}
