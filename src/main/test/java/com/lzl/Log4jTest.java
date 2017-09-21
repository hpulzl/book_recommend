package com.lzl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: li_zhilei
 * @Date: create in 11:18 17/6/7.
 * @description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RecommendApplication.class)
public class Log4jTest {
    @Test
    public void test(){
        Logger logger = LoggerFactory.getLogger(Log4jTest.class);
        logger.info("你好.....");
    }
}
