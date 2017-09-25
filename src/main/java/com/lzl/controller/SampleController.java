package com.lzl.controller;

import com.lzl.redisdao.UseRedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: li_zhilei
 * @Date: create in 17:16 17/5/23.
 * @description:
 */
@RestController
@RequestMapping("sample")
public class SampleController {

    @Autowired
    private UseRedisDao useRedisDao;

    @RequestMapping("hi")
    public String sayHello(String key,String value){
        useRedisDao.setValue(key,value);
        return useRedisDao.getValue(key);
    }
}
