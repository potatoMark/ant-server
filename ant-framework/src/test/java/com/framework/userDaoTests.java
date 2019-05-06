package com.framework;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.framework.modules.sys.dao.MenuDao;
import com.framework.modules.sys.dao.RoleDao;
import com.framework.modules.sys.dao.UserDao;
import com.framework.modules.sys.dao.UserMapper;
import com.framework.modules.sys.pojo.User;
import com.framework.modules.sys.service.IUserService;
import com.framework.modules.sys.vo.UserVO;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

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



    @Test
    public void testSelect() {

        UserVO userVO = new UserVO();
        userVO.setUsername("马");

        List<User> userList = userDao.selectList(new QueryWrapper<User>()
                .like(StringUtils.isNotBlank(userVO.getUsername()),"username",userVO.getUsername())
                .like(StringUtils.isNotBlank(userVO.getUsername()),"usercode",userVO.getUsercode())
                .like(StringUtils.isNotBlank(userVO.getUsername()),"sex",userVO.getSex())
                .like(StringUtils.isNotBlank(userVO.getUsername()),"phone",userVO.getPhone())
                .like(StringUtils.isNotBlank(userVO.getUsername()),"status",userVO.getStatus())
        );
//        Page<User> page = new Page(1L,2);
//        IPage<User> p= userDao.selectPage(page,new QueryWrapper<User>().like("username",userVO.getUsername()));
        System.out.println(userList);

    }
}
