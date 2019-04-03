package com.framework;

import com.framework.modules.sys.dao.UserDao;
import com.framework.service.UserServiceImplTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class multipleDataSourceTests {

    @Autowired
    private UserServiceImplTest userServiceImplTest;

    @Test
    public void test() {
        System.out.println(userServiceImplTest.queryUser(Long.valueOf(1)).getUsername());

        System.out.println(userServiceImplTest.queryUser2(Long.valueOf(1)).getUsername());

        userServiceImplTest.insertUser();
    }
}
