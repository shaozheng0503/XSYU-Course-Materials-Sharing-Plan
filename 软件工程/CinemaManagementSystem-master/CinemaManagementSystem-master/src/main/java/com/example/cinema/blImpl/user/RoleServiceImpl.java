package com.example.cinema.blImpl.user;

import com.example.cinema.bl.user.RoleService;
import com.example.cinema.data.user.AccountMapper;
import com.example.cinema.po.User;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hxw
 * @date 2019/5/20
 */
@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public ResponseVO getAdaptableRoles(Integer level) {
        try {
            List<User> roles = accountMapper.getCinemaRoles();
            List<RoleVO> res = new ArrayList<>();
            for (User user : roles) {
                RoleVO role = new RoleVO();
                role.setId(user.getId());
                role.setUsername(user.getUsername());

                if (user.getAuth() >= level) {
                    role.setPassword("******");
                } else {
                    role.setPassword(user.getPassword());
                }

                if (user.getAuth() == User.AUTH_ADMIN) {
                    role.setAuth("影院员工");
                } else if (user.getAuth() == User.AUTH_MANAGER) {
                    role.setAuth("管理员");
                } else { }

                res.add(role);
            }
            return ResponseVO.buildSuccess(res);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("获取影院角色失败!");
        }
    }

    @Override
    public ResponseVO deleteRole(Integer id) {
        try {
            accountMapper.deleteRoleById(id);
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            return ResponseVO.buildFailure("删除角色失败!");
        }
    }

    @Override
    public ResponseVO editRole(RoleVO form) {
        try {
            User user = accountMapper.getAccountByName(form.getUsername());
            if (user != null && user.getId() != form.getId()) {
                return ResponseVO.buildFailure("用户名已存在!");
            }
            accountMapper.updateRoleById(form.getId(),form.getUsername(),form.getPassword(),getAuthByAuthStr(form.getAuth()));
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            return ResponseVO.buildFailure("修改信息失败!");
        }
    }

    @Override
    public ResponseVO addRole(RoleVO form) {
        try {
            User user = accountMapper.getAccountByName(form.getUsername());
            if (user != null) {
                return ResponseVO.buildFailure("用户名已存在!");
            }
            accountMapper.createNewAccount(form.getUsername(),form.getPassword(),getAuthByAuthStr(form.getAuth()));
            return ResponseVO.buildSuccess();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseVO.buildFailure("新增角色失败!");
        }
    }

    public Integer getAuthByAuthStr(String authStr) {
        Integer auth = User.AUTH_ADMIN;
        if (authStr.equals("影院员工")) {
            auth = User.AUTH_ADMIN;
        } else if (authStr.equals("管理员")) {
            auth = User.AUTH_MANAGER;
        } else if (authStr.equals("取消员工")) {
            auth = User.AUTH_AUDIENCE;
        }
        return auth;
    }
}
