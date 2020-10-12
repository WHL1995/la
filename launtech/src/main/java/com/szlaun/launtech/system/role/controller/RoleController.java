package com.szlaun.launtech.system.role.controller;

import com.szlaun.launtech.anno.Authority;
import com.szlaun.launtech.system.role.dto.Role;
import com.szlaun.launtech.system.role.service.RoleService;
import com.szlaun.launtech.system.user.dto.User;
import com.szlaun.launtech.utils.Constant;
import com.szlaun.launtech.utils.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/select")
    @Authority({"{role:select}"})
    @ResponseBody
    public ResultMsg select() {
        List<Role> roles = roleService.selectAll();
        if (roles.size() > 0) {
            return ResultMsg.getSuccess("操作成功", roles);
        }
        return ResultMsg.getSuccess("查询数据为空");
    }

    @RequestMapping("/insert")
    @Authority({"role:insert"})
    @ResponseBody
    public ResultMsg insert(HttpServletRequest request, @RequestParam(required = true) Role role) {
        User user = (User) request.getSession().getAttribute(Constant.SESSION_ACCOUNT_FLAGE);
        if (user != null && role != null) {
            int result = roleService.insert(role);
            if (result > 0) {
                return ResultMsg.getSuccess();
            }
        }

        return ResultMsg.getError();
    }

    @RequestMapping("/update")
    @Authority({"role:update"})
    @ResponseBody
    public ResultMsg update(HttpServletRequest request, @RequestParam(required = true) Role role) {
        User user = (User) request.getSession().getAttribute(Constant.SESSION_ACCOUNT_FLAGE);
        if (user != null && role != null) {
            int result = roleService.update(role);
            if (result > 0) {
                return ResultMsg.getSuccess();
            }
        }
        return ResultMsg.getError();
    }

    @RequestMapping("/delete")
    @Authority({"role:delete"})
    @ResponseBody
    public ResultMsg delete(@RequestParam(required = true) String ids) {
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
