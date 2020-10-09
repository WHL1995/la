package com.szlaun.launtech.system.role.controller;

import com.szlaun.launtech.system.role.dto.Role;
import com.szlaun.launtech.system.role.service.RoleService;
import com.szlaun.launtech.utils.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/RoleController")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/RoleController_doAdd")
    @ResponseBody
    public ResultMsg doAdd(Role role) {
        int result = roleService.insert(role);
        if (result > 0) {
            return ResultMsg.getSuccess();
        }
        return ResultMsg.getError();
    }

    @RequestMapping("/RoleController_doEdit")
    @ResponseBody
    public ResultMsg doEdit(Role role) {
        int result = roleService.update(role);
        if (result > 0) {
            return ResultMsg.getSuccess();
        }
        return ResultMsg.getError();
    }

    @RequestMapping("/RoleController_doRomve")
    public ResultMsg doRomve(String ids) {
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
