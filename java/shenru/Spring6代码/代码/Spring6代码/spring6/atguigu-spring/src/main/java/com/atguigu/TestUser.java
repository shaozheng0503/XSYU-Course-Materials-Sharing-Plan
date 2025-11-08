package com.atguigu;

import com.atguigu.bean.AnnotationApplicationContext;
import com.atguigu.bean.ApplicationContext;
import com.atguigu.service.UserService;

public class TestUser {

    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationApplicationContext("com.atguigu");
        UserService userService = (UserService)context.getBean(UserService.class);
        System.out.println(userService);
        userService.add();
    }
}
