package com.atguigu.spring6.iocxml.auto.dao;

public class UserDaoImpl  implements UserDao{

    @Override
    public void addUserDao() {
        System.out.println("userDao方法执行了...");
    }
}
