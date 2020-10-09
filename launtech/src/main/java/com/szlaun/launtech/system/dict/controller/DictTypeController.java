package com.szlaun.launtech.system.dict.controller;

import com.alibaba.fastjson.JSONObject;
import com.szlaun.launtech.anno.Authority;
import com.szlaun.launtech.system.dict.dto.DictType;
import com.szlaun.launtech.system.dict.service.DictTypeService;
import com.szlaun.launtech.system.user.dto.User;
import com.szlaun.launtech.utils.Constant;
import com.szlaun.launtech.utils.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/dictType")
public class DictTypeController {

    @Autowired
    private DictTypeService dictTypeService;

    @PostMapping("/insert")
    @Authority({"dictType:insert"})
    @ResponseBody
    public ResultMsg insert(HttpServletRequest request, @RequestParam(required = true) DictType dictType) {
        User user = (User) request.getSession().getAttribute(Constant.SESSION_ACCOUNT_FLAGE);
        if (user != null && dictType != null) {
            int result = dictTypeService.insert(dictType);
            if (result > 0) {
                return ResultMsg.getSuccess();
            }
        }

        return ResultMsg.getError();
    }

    @PostMapping("/update")
    @Authority({"dictType:update"})
    @ResponseBody
    public ResultMsg update(HttpServletRequest request, @RequestParam(required = true) DictType dictType) {
        User user = (User) request.getSession().getAttribute(Constant.SESSION_ACCOUNT_FLAGE);
        if (user != null && dictType != null) {
            int result = dictTypeService.update(dictType);
            if (result > 0) {
                return ResultMsg.getSuccess();
            }
        }
        return ResultMsg.getError();
    }

    @PostMapping("/delete")
    @Authority({"dictType:delete"})
    @ResponseBody
    public ResultMsg delete(@RequestParam(required = true) String ids) {
        JSONObject jsonObject = new JSONObject();

        int result = 0;
        try {
            result = dictTypeService.deleteByIds(ids);
        } catch (Exception e) {


            return ResultMsg.getError(e.getMessage());
        }
        if (result > 0) {
            return ResultMsg.getSuccess();

        }
        return ResultMsg.getError();
    }

}
