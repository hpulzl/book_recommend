package com.lzl;

import com.lzl.service.TransactionService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: li_zhilei
 * @Date: create in 16:13 17/10/10.
 * @description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RecommendApplication.class)
public class TransactionTest {
    @Autowired
    private TransactionService transactionService;

    public void readUnCommitTest(){
        //一个事务进行查询操作，另一个事务进行插入操作。查询出的数据包括另一个事务中未提交的数据
    }
}
