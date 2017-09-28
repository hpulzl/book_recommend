package com.lzl.controller;

import com.lzl.redisdao.UseRedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

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

    @RequestMapping("login")
    public String login(String userName, String password){
        HttpSession httpSession = ((ServletRequestAttributes)RequestContextHolder
                .getRequestAttributes()).getRequest().getSession();
        httpSession.setAttribute("userName", userName);
        return (String) httpSession.getAttribute("userName");
    }
}
