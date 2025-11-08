package com.atguigu.schedule.service;

import com.atguigu.schedule.pojo.SysUser;
/**
 * 该接口定义了以sys_user表格为核心的业务处理功能
 */
public interface SysUserService {

    /**
     * 注册用户的方法
     * @param sysUser 要注册的用户名和明文密码以SysUser对象的形式接收
     * @return 注册成功返回1 注册失败返回0
     */
    int regist(SysUser sysUser);

    /**
     * 根据用户名获得完整用户信息的方法
     * @param username 要查询的用户名
     * @return 如果找到了返回SysUser对象,找不到返回null
     */
    SysUser findByUsername(String username);
}
