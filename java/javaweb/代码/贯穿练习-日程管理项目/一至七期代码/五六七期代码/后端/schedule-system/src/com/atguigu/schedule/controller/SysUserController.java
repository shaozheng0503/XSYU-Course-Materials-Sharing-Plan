package com.atguigu.schedule.controller;

import com.atguigu.schedule.common.Result;
import com.atguigu.schedule.common.ResultCodeEnum;
import com.atguigu.schedule.pojo.SysUser;
import com.atguigu.schedule.service.SysUserService;
import com.atguigu.schedule.service.impl.SysUserServiceImpl;
import com.atguigu.schedule.util.MD5Util;
import com.atguigu.schedule.util.WebUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/user/*")
public class SysUserController  extends BaseContoller {

    private SysUserService userService =new SysUserServiceImpl();


    /**
     * 注册时,接收要注册的用户名,校验用户名是否被占用的业务接口
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void checkUsernameUsed(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接收用户名
        String username = req.getParameter("username");

        // 调用服务层业务处理方法查询该用于名是否有对应的用户
        SysUser sysUser = userService.findByUsername(username);
        // 如果有 响应 已占用
        // 如果没有 响应 可用

        Result result =Result.ok(null);

        if(null != sysUser){
           result=Result.build(null, ResultCodeEnum.USERNAME_USED);
        }
        // 将result对象转换为JSON串响应给客户端
        WebUtil.writeJson(resp,result);
    }

    /**
     * 接收用登录请求,完成的登录业务接口
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 接收用户名和密码
        SysUser sysUser = WebUtil.readJson(req, SysUser.class);
        //2 调用服务层方法,根据用户名查询用户信息
        SysUser loginUser =userService.findByUsername(sysUser.getUsername());

        Result result =null;

        if(null == loginUser){
            result =Result.build(null,ResultCodeEnum.USERNAEM_ERROR);

        } else if(! MD5Util.encrypt(sysUser.getUserPwd()).equals(loginUser.getUserPwd())){
            result =Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
        }else{
            // 登录程序,将用户uid和username响应给客户端
            Map data = new HashMap();
            loginUser.setUserPwd("");
            data.put("loginUser",loginUser);

            result =Result.ok(data);
        }
        // 3将登录结果响应给客户端
        WebUtil.writeJson(resp,result);

    }

    /**
     * 接收用户注册请求的业务处理方法( 业务接口 不是java中的interface  )
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 1 接收客户端提交的json参数,并转换为User对象,获取信息
        SysUser registUer = WebUtil.readJson(req, SysUser.class);

        // 2 调用服务层方法,完成注册功能
            //将参数放入一个SysUser对象中,在调用regist方法时传入
        int rows =userService.regist(registUer);
        // 3 根据注册结果(成功  失败) 做页面跳转
        Result result = Result.ok(null);
        if(rows <1){
            result=Result.build(null,ResultCodeEnum.USERNAME_USED);
        }
        WebUtil.writeJson(resp,result);
    }
}
