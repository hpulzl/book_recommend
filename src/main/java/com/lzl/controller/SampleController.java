package com.lzl.controller;

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
    @RequestMapping("hi")
    public String sayHello(){
        return "太热就提交两个都是";
    }
}
