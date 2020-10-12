package com.szlaun.launtech.system.dict.controller;


import com.szlaun.launtech.anno.Authority;
import com.szlaun.launtech.system.dict.dto.Dict;
import com.szlaun.launtech.system.dict.service.DictService;
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
import java.util.List;

@Controller
@RequestMapping("/dict")
public class DictController {

    @Autowired
    private DictService dictService;

    @RequestMapping("/select")
    @Authority({"dict:select"})
    @ResponseBody
    public ResultMsg select() {
        List<Dict> dict = dictService.selectAll();
        if (dict.size()>0) {
            return ResultMsg.getSuccess("操作成功", dict);
        }
        return ResultMsg.getError("查询数据为空");
    }

    @PostMapping("/insert")
    @Authority({"dict:insert"})
    @ResponseBody
    public ResultMsg insert(HttpServletRequest request, @RequestParam(required = true) Dict dict) {
        User user = (User) request.getSession().getAttribute(Constant.SESSION_ACCOUNT_FLAGE);
        if (user != null && dict != null) {
            dict.setCreateUser(user.getName());
            int result = dictService.insert(dict);
            if (result > 0) {
                return ResultMsg.getSuccess();
            }
        }

        return ResultMsg.getError();
    }

    @PostMapping("/update")
    @Authority({"dict:update"})
    @ResponseBody
    public ResultMsg update(HttpServletRequest request, @RequestParam(required = true) Dict dict) {
        User user = (User) request.getSession().getAttribute(Constant.SESSION_ACCOUNT_FLAGE);
        if (user != null && dict != null) {
            int result = dictService.update(dict);
            if (result > 0) {
                return ResultMsg.getSuccess();
            }
        }
        return ResultMsg.getError();
    }

    @RequestMapping("/delete")
    @Authority({"dict:delete"})
    @ResponseBody
    public ResultMsg delete(@RequestParam(required = true) String ids) {
        int result = dictService.deleteByIds(ids);
        if (result > 0) {
            return ResultMsg.getSuccess();
        }
        return ResultMsg.getError();
    }
}
