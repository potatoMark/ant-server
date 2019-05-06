package com.framework;

import com.framework.modules.sys.dao.MenuDao;
import com.framework.modules.sys.dao.RoleDao;
import com.framework.modules.sys.dao.UserDao;
import com.framework.modules.sys.dao.UserMapper;
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
public class jdbcTests {

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


    @Test
    public void testSelect() {
//        List<UserVO> userList = userDao.selectList(null);
//        userList.forEach(
//            (sysUser)->{
//                System.out.println(sysUser.getUsername()+" "+sysUser.getLastlogin());
//            }
//        );
//        List<UserVO> userList = userDao.selectList(new QueryWrapper<UserVO>()
//            .in("sex",1));
//        userList.forEach(
//            (sysUser)->{
//                System.out.println(sysUser.getUsername()+" "+sysUser.getLastlogin());
//            }
//        );
//        UserVO user = userDao.loadUserByUsername("马克");
//        System.out.println(user.getAuthorities());
//        List<Menu> ms = menuDao.getMenuListWhereParam("ROLE_ADMIN");
//        System.out.println(ms);
//          User user = userDao.loadUserById(Long.valueOf(1));

//        Map<String, Object> params = new HashMap<String, Object>();
//        params.put("currentPage","1");
//        params.put("limit","2");
//        User user = new User();
//        user.setSex(1);
//        IPage<User> iPage = userMapper.loadUserPage(new Page<User>(1,2), user);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("123456"));

        Map<String, Object> params = new HashMap<String, Object>();
        params.put("_page","1");
        params.put("_limit","2");
//        params.put("username","李四");
        params.put("usercode","00002");
//        PageUtils pageUtils = userService.queryPage(params);
//        System.out.println(pageUtils);
    }
}
