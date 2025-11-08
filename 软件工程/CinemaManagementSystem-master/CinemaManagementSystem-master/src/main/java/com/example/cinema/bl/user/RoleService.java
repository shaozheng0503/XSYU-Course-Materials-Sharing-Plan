package com.example.cinema.bl.user;

import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.RoleVO;

/**
 * @author hxw
 * @date 2019/5/20
 */
public interface RoleService {

    /**
     * 获取当前身份等级可修改的影院角色(同级或更高级只可见用户名)
     * @param level
     * @return
     */
    ResponseVO getAdaptableRoles(Integer level);

    /**
     * 删除角色
     * @param id
     * @return
     */
    ResponseVO deleteRole(Integer id);

    /**
     * 修改角色信息
     * @param form
     * @return
     */
    ResponseVO editRole(RoleVO form);

    /**
     * 添加角色
     * @param form
     * @return
     */
    ResponseVO addRole(RoleVO form);

}
