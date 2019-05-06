package com.framework;

import com.framework.common.utils.RedisUtil;
import com.framework.modules.sys.dao.UserDao;
import com.framework.modules.sys.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class redisTokenTests {

    @Autowired
    private UserDao userDao;

    @Autowired
    RedisUtil util;


    @Test
    public void testSelect() {

        User user = userDao.loadUserById(1L);
        user.getId();

        if (util.set("abc",user.getId(),5)){
            try{

                Thread.sleep(2*1000);
                Long l = (Long)util.get("abc");
                System.out.println("abc is "+l.intValue());
                Thread.sleep(10*1000);

                System.out.println("abc is "+util.get("abc"));
            }catch (Exception e){
                e.printStackTrace();
            }
        }


    }
}
