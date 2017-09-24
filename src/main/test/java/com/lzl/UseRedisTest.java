package com.lzl;

import com.lzl.bean.User;
import com.lzl.config.RedisCacheConfig;
import com.lzl.redisdao.UseRedisDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: li_zhilei
 * @Date: create in 15:55 17/9/21.
 * @description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RecommendApplication.class)
public class UseRedisTest {

    @Autowired
    private UseRedisDao useRedisDao;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    public void setValueTest(){
        useRedisDao.setValue("sfsg", "gfdsgf");
        String value = useRedisDao.getValue("sfsg");
        System.out.println("value = " + value);
    }

    @Test
    public void setListTest(){
        useRedisDao.addList("b","dss");
        useRedisDao.addList("b","sfdsss");
        useRedisDao.addList("b","fdsadf");
        Long size = useRedisDao.getListSize("a");
        System.out.println("size = " + size);
    }

    @Test
    public void setHashMapTest(){
        User female = new User();
        female.setId(1);
        female.setAccount("xiaohong123");
        female.setName("xiaohong");
        female.setSex("female");
        User male = new User();
        male.setId(2);
        male.setName("llzl");
        male.setAccount("lzl123");
        male.setSex("male");
        Map<String,Object> map = new HashMap<String,Object>();
        map.put(male.getId()+"", male);
        map.put(female.getId()+"", female);

        useRedisDao.setHashMap(map.hashCode()+"", map);

        Map map1 = useRedisDao.getHashMap(map.hashCode()+"");
        System.out.println("map1 = " + map1);
    }

    @Test
    public void stringTest(){
        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("hello", "redis");
        System.out.println("useRedisDao = " + valueOperations.get("hello"));
    }
}
