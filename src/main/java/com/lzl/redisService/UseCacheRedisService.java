package com.lzl.redisService;

import com.lzl.bean.User;
import com.lzl.cache.SaveUserInfo;
import com.lzl.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: li_zhilei
 * @Date: create in 15:54 17/9/22.
 * @description:
 * 对springCache小试牛刀。
 */
@CacheConfig(cacheNames = "user")
@Service
public class UseCacheRedisService {
    @Autowired
    private UserMapper userMapper;


    /**
     * 设置cacheable的注解
     * @return
     */
    @Cacheable
    public List<User> selectAllUser(){
        return userMapper.selectAll();
    }

    /**
     * 单个user对象的插入操作，使用user+id
     * @param user
     * @return
     */
    @CachePut(key = "\"user_\" + #user.id")
    public User saveUser(User user){
        userMapper.insert(user);
        return user;
    }

    /**
     * 值得注意的是，这里的update和add方法都需要有返回值。
     * 如果数据库中没有该id值，缓存中照样会存入这个user对象的信息
     * @param user
     */
    @CachePut(key = "\"user_\" + #user.id")
    public User updateUser(User user){
        userMapper.updateByPrimaryKey(user);
        user = userMapper.selectByPrimaryKey(user);
        return user;
    }

    @CacheEvict(key = "\"user_\" + #id")
    public void deleteById(Integer id){
        userMapper.deleteByPrimaryKey(id);
    }

    @CacheEvict(allEntries = true)
    public void deleteAll(){
        userMapper.deleteAll();
    }

    //TODO 思考，如果我想删除指定条件的多个user对象呢？该如何同时删除缓存中的数据呢？

    @Caching(
            put = {
                    @CachePut(value = "user", key = "\"user_\" + #user.id"),
                    @CachePut(value = "user", key = "#user.name"),
                    @CachePut(value = "user", key = "#user.account")
            }
    )
    public User saveUserByCaching(User user){
        userMapper.insert(user);
        return user;
    }

    @SaveUserInfo
    public User saveUserByInfo(User user){
        userMapper.insert(user);
        return user;
    }

    public void doSome(){
        System.out.println("userMapper = " + userMapper);
    }

    /**
     * 使用自定义生成的key，用于错误演示
     * @param user
     * @return
     */
    @CachePut
    public User saveUserByErr(User user){
        userMapper.insert(user);
        return user;
    }
}
