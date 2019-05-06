package com.framework;

import com.framework.modules.sys.dao.MenuDao;
import com.framework.modules.sys.dao.RoleDao;
import com.framework.modules.sys.dao.UserDao;
import com.framework.modules.sys.dao.UserMapper;
import com.framework.modules.sys.service.IMenuService;
import com.framework.modules.sys.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class roleDaoTests {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private IUserService userService;

    @Autowired
    private IMenuService menuService;

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
//        Role role = roleDao.findById(1L);
//        System.out.print(role);
    }
}
