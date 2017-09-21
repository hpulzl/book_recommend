package com.lzl.task;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan("com.lzl.task")
@EnableScheduling //1 开启对计划任务的支持
public class TaskSchedulerConfig {

}
