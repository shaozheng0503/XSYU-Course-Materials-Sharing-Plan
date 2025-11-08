package com.example.cinema.blImpl.user;

import com.example.cinema.bl.user.AccountService;
import com.example.cinema.data.user.AccountMapper;
import com.example.cinema.po.User;
import com.example.cinema.vo.UserForm;
import com.example.cinema.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 定义用户身份为常量
 * @author hxw
 * @date 2019/5/20
 */
@Service
public class AccountServiceImpl implements AccountService {
    private final static String ACCOUNT_EXIST = "账号已存在";
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public ResponseVO registerAccount(UserForm userForm) {
        try {
            accountMapper.createNewAccount(userForm.getUsername(), userForm.getPassword(), User.AUTH_AUDIENCE);
        } catch (Exception e) {
            return ResponseVO.buildFailure(ACCOUNT_EXIST);
        }
        return ResponseVO.buildSuccess();
    }

    @Override
    public User login(UserForm userForm) {
        User user = accountMapper.getAccountByName(userForm.getUsername());
        if (null == user || !user.getPassword().equals(userForm.getPassword())) {
            return null;
        }
        return user;
    }

    @Override
    public ResponseVO checkPassword(User user, String rawPassword) {
        if (user.getPassword().equals(rawPassword)) {
            return ResponseVO.buildSuccess();
        } else {
            return ResponseVO.buildFailure("失败");
        }
    }

    @Override
    public ResponseVO editPassword(UserForm userForm) {
        try {
            int res = accountMapper.updatePassword(userForm);
            if (res == 1) {
                return ResponseVO.buildSuccess();
            } else {
                return ResponseVO.buildFailure("修改密码失败!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("修改密码失败!");
        }
    }
}
