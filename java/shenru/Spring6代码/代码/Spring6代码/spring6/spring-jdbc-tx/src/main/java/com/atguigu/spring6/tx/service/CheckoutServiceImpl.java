package com.atguigu.spring6.tx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CheckoutServiceImpl implements CheckoutService{

    //注入bookService
    @Autowired
    private BookService bookService;

    //买多本书的方法
    @Transactional
    @Override
    public void checkout(Integer[] bookIds, Integer userId) {
        for(Integer bookId:bookIds) {
            //调用service的方法
            bookService.buyBook(bookId,userId);
        }
    }
}
