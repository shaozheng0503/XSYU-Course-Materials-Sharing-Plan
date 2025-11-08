package com.example.cinema.controller.user;

import com.example.cinema.bl.user.RoleService;
import com.example.cinema.vo.ResponseVO;
import com.example.cinema.vo.RoleVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author hxw
 * @date 2019-5-20
 */
@RestController
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("/role/get")
    public ResponseVO getAdaptableRoles(@RequestParam("level") Integer level) {
        return roleService.getAdaptableRoles(level);
    }

    @GetMapping("/role/delete")
    public ResponseVO deleteRole(@RequestParam("id") Integer id) {
        return roleService.deleteRole(id);
    }

    @PostMapping("/role/edit")
    public ResponseVO editRole(@RequestBody RoleVO form) {
        return roleService.editRole(form);
    }

    @PostMapping("/role/add")
    public ResponseVO addRole(@RequestBody RoleVO form) {
        return roleService.addRole(form);
    }
}
