package com.lzl.service;

import com.lzl.bean.Books;
import com.lzl.mapper.BooksMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

/**
 * @Author: li_zhilei
 * @Date: create in 16:01 17/5/25.
 * @description:
 */
@Service
public class BooksService {
    @Autowired
    private BooksMapper booksMapper;

    @Transactional
    public void save(Books books){
        Assert.notNull(books.getName(),"书名不能为空");
        booksMapper.insert(books);
        List<Books> list = booksMapper.selectAll();
        System.out.println("list = " + list);
    }
    public void batchSave(List<Books> list){
        Assert.notNull(list,"list 集合不能为空");
        booksMapper.insertList(list);
    }
}
