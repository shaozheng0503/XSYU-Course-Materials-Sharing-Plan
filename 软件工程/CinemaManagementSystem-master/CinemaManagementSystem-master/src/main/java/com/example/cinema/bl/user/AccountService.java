package com.example.cinema.bl.user;

import com.example.cinema.po.User;
import com.example.cinema.vo.UserForm;
import com.example.cinema.vo.ResponseVO;

/**
 * @author hxw
 * @date 2019/5/4
 */
public interface AccountService {

    /**
     * 注册账号
     * @return
     */
    public ResponseVO registerAccount(UserForm userForm);

    /**
     * 用户登录，登录成功会将用户信息保存在session中
     * @return
     */
    public User login(UserForm userForm);

    /**
     * 检测密码是否正确
     * @param user
     * @param rawPassword
     * @return
     */
    public ResponseVO checkPassword(User user, String rawPassword);

    /**
     * 修改密码
     * @param userForm
     * @return
     */
    public ResponseVO editPassword(UserForm userForm);

}
