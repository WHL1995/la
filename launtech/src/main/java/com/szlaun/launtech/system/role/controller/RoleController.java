package com.szlaun.launtech.system.role.controller;

import com.szlaun.launtech.anno.Authority;
import com.szlaun.launtech.system.role.dto.Role;
import com.szlaun.launtech.system.role.service.RoleService;
import com.szlaun.launtech.utils.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/insert")
    @Authority({"role:insert"})
    @ResponseBody
    public ResultMsg insert(Role role) {
        int result = roleService.insert(role);
        if (result > 0) {
            return ResultMsg.getSuccess();
        }
        return ResultMsg.getError();
    }

    @RequestMapping("/update")
    @Authority({"role:update"})
    @ResponseBody
    public ResultMsg update(Role role) {
        int result = roleService.update(role);
        if (result > 0) {
            return ResultMsg.getSuccess();
        }
        return ResultMsg.getError();
    }

    @RequestMapping("/delete")
    @Authority({"role:delete"})
    @ResponseBody
    public ResultMsg delete(String ids) {
        int result = 0;
        try {
            result = roleService.delete(ids);
            if (result > 0) {
                return ResultMsg.getSuccess();
            }
        } catch (Exception e) {
            return ResultMsg.getError(e.getMessage());
        }
        return ResultMsg.getError();

    }
}
