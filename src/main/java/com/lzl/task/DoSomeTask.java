package com.lzl.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Author: li_zhilei
 * @Date: create in 14:45 17/9/8.
 * @description:
 */
@Component
public class DoSomeTask {

    @Scheduled(fixedRate = 5000)
    public void doTask(){
        System.out.println("true = " + true);
    }
}
