package com.example.cinema.data.user;

import com.example.cinema.po.User;
import com.example.cinema.vo.RoleVO;
import com.example.cinema.vo.UserForm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author hxw
 * @date 2019/5/19
 */
@Mapper
public interface AccountMapper {

    /**
     * 创建一个新的账号
     * @param username
     * @param password
     * @return
     */
    int createNewAccount(@Param("username") String username, @Param("password") String password, @Param("auth") Integer auth);

    /**
     * 根据用户名查找账号
     * @param username
     * @return
     */
    User getAccountByName(@Param("username") String username);

    /**
     * 更新密码
     * @param userForm
     * @return
     */
    int updatePassword(@Param("userForm") UserForm userForm);

    /**
     * 获取全部影院角色
     * @return
     */
    List<User> getCinemaRoles();

    /**
     * 根据id删除角色
     * @param id
     * @return
     */
    int deleteRoleById(@Param("id") Integer id);

    /**
     * 更新角色信息
     * @param id
     * @param username
     * @param password
     * @param auth
     * @return
     */
    int updateRoleById(@Param("id") Integer id, @Param("username") String username,
                       @Param("password") String password, @Param("auth") Integer auth);

}
