package com.framework;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class redisConnectionTests {

    @Autowired
    RedisTemplate redisTemplate;


    @Test
    public void test(){

        redisTemplate.opsForValue().set("test1","value1");
        redisTemplate.opsForValue().set("test2","中文",5, TimeUnit.SECONDS);

        System.out.println(redisTemplate.opsForValue().get("test1"));
        System.out.println(redisTemplate.opsForValue().get("test2"));

        try{
            Thread.sleep(6000);
            System.out.println(redisTemplate.opsForValue().get("test2"));
        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
