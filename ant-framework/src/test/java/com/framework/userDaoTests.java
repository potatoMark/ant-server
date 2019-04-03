package com.framework;

import com.framework.modules.demo.dao.TablesDao;
import com.framework.modules.sys.dao.MenuDao;
import com.framework.modules.sys.dao.RoleDao;
import com.framework.modules.sys.dao.UserDao;
import com.framework.modules.sys.dao.UserMapper;
import com.framework.modules.sys.pojo.User;
import com.framework.modules.sys.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class userDaoTests {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private IUserService userService;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TablesDao tablesDao;


    @Test
    public void testSelect() {

        User user = userDao.loadUserById(1L);
        user.getId();
    }
}
