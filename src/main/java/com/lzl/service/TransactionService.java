package com.lzl.service;

import com.lzl.bean.Books;
import com.lzl.mapper.BooksMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: li_zhilei
 * @Date: create in 15:20 17/10/10.
 * @description:
 */
@Service
public class TransactionService {

    @Autowired
    private BooksMapper booksMapper;

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public List<Books> listBooks(){
        return booksMapper.selectAll();
    }

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public void insertBooks(Books books){
        booksMapper.insert(books);
    }
}
