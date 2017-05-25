package com.lzl.service;

import com.lzl.bean.Books;
import com.lzl.mapper.BooksMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @Author: li_zhilei
 * @Date: create in 16:01 17/5/25.
 * @description:
 */
@Service
public class BooksService {
    @Autowired
    private BooksMapper booksMapper;

    public void save(Books books){
        Assert.notNull(books.getName(),"书名不能为空");
        booksMapper.insert(books);
    }
}
