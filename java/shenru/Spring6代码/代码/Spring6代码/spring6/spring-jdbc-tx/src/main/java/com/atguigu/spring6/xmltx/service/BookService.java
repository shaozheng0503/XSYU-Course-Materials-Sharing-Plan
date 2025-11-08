package com.atguigu.spring6.xmltx.service;

public interface BookService {

    //买书的方法：图书id和用户id
    void buyBook(Integer bookId, Integer userId);
}
