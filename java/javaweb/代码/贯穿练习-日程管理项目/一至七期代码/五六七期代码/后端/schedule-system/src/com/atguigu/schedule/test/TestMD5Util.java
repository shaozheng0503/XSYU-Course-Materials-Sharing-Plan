package com.atguigu.schedule.test;

import com.atguigu.schedule.util.MD5Util;
import org.testng.annotations.Test;

public class TestMD5Util {
    @Test
    public void testEncrypt(){
        String encrypt = MD5Util.encrypt("123456");
        System.out.println(encrypt);
    }
}
