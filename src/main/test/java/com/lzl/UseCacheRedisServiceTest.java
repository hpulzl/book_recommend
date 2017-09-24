package com.lzl;

import com.lzl.bean.User;
import com.lzl.redisService.UseCacheRedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Author: li_zhilei
 * @Date: create in 10:38 17/9/24.
 * @description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RecommendApplication.class)
public class UseCacheRedisServiceTest {

    @Autowired
    private UseCacheRedisService useCacheRedisService;

    @Test
    public void useTest(){
        User user = new User();
        user.setName("fsdfds");
        user.setSex("fdsfg");
        user.setAccount("gfds");
        user.setPassword("gfdsg");
        user.setAge(24);
        useCacheRedisService.saveUser(user);
    }

    @Test
    public void saveErrTest(){
        User user = new User();
        user.setName("fsdsg");
        user.setSex("vcxvx");
        user.setAccount("vxcvxc");
        user.setPassword("vcxvcxc");
        user.setAge(24);
        useCacheRedisService.saveUserByErr(user);
    }

    @Test
    public void selectUserTest(){
        System.out.println("===========第一次调用=======");
        List<User> list = useCacheRedisService.selectAllUser();
        for (User u : list){
            System.out.println("u = " + u);
        }
        System.out.println("===========调用save方法=======");
        User user = new User();
        user.setName("abfm");
        user.setSex("sadf");
        user.setAccount("fdas");
        user.setPassword("fdasfsa");
        user.setAge(24);
        useCacheRedisService.saveUser(user);
        System.out.println("===========第二次调用===========" );
        List<User> list2 = useCacheRedisService.selectAllUser();
        for (User u : list2){
            System.out.println("u = " + u);
        }
    }

    @Test
    public void selectTest(){
        System.out.println("===========第一次调用=======");
        List<User> list = useCacheRedisService.selectAllUser();
        System.out.println("===========第二次调用=======");
        List<User> list2 = useCacheRedisService.selectAllUser();
        for (User u : list2){
            System.out.println("u = " + u);
        }
    }

    @Test
    public void updateUserTest(){
        User user = new User();
        user.setId(1);
        user.setName("xixi");
        user.setAge(25);
        useCacheRedisService.updateUser(user);
    }
    
    @Test
    public void deleteTest(){
        useCacheRedisService.deleteById(1);
    }
    @Test
    public void deleteAllTest(){
        useCacheRedisService.deleteAll();
    }
    @Test
    public void saveUserByInfoTest(){
        User user = new User();
        user.setName("haha");
        user.setAccount("hhhcc");
        useCacheRedisService.saveUserByInfo(user);
    }
    @Test
    public void saveUserByCachingTest(){
        User user = new User();
        user.setName("dkjd");
        user.setAccount("dsjkf");
        useCacheRedisService.saveUserByCaching(user);
    }
    @Test
    public void doSomeTest(){
        useCacheRedisService.doSome();
    }
}
