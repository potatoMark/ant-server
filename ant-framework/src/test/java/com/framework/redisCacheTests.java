package com.framework;

import com.framework.modules.sys.pojo.User;
import com.framework.modules.sys.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class redisCacheTests {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    IUserService userService;


    @Test
    public void test(){

        //insert
        User user = new User();

        user.setUsername("x");
        user.setUsercode("001");
        user.setPassword("12");
        user.setSex(1);

        Long id = userService.insertUser(user).getId();

        //get
        User userx = userService.getUser(Long.valueOf(id));
        System.out.println(userx!=null?userx.getUsername():null);

        //update
        User users = new User();

        users.setId(Long.valueOf(id));
        users.setUsername("ccc");
        users.setUsercode("asd");
        users.setPassword("sasadsa");
        users.setSex(1);

        userService.updateUser(users);

        User userxx = userService.getUser(Long.valueOf(id));
        System.out.println(userxx!=null?userxx.getUsername():null);

        //delete
        userService.deleteUser(Long.valueOf(id));
        User userxx1 = userService.getUser(Long.valueOf(id));
        System.out.println(userxx1!=null?userxx1.getUsername():null);

    }



}
