package com.lzl.redisdao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.Map;


/**
 * @Author: li_zhilei
 * @Date: create in 15:44 17/9/21.
 * @description:整合redis的实例,简单了解RedisTemplate的API
 */
@Repository
public class UseRedisDao {

    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;
    private ValueOperations<Object,Object> valueOperations;
    private ListOperations<Object,Object> listOperations;
    private HashOperations hashOperations;

    @PostConstruct
    public void getValueOperation(){
        valueOperations = redisTemplate.opsForValue();
        listOperations = redisTemplate.opsForList();
        hashOperations = redisTemplate.opsForHash();
    }

    public void setValue(String key, String value){
        valueOperations.set(key, value);
    }

    public String getValue(String key){
        return (String) valueOperations.get(key);
    }

    public void addList(String key, String value){
        listOperations.leftPush(key, value);
    }
    public Long getListSize(String key){
        return listOperations.size(key);
    }

    public void setHashMap(String key, Map map){
        hashOperations.putAll(key, map);
    }
    public Map getHashMap(String key){
        return hashOperations.entries(key);
    }
}
