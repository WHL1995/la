package com.szlaun.launtech.system.dict.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.szlaun.launtech.anno.Authority;
import com.szlaun.launtech.system.dict.dto.Dict;
import com.szlaun.launtech.system.dict.service.DictService;
import com.szlaun.launtech.utils.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/DictController")
public class DictController {

    @Autowired
    private DictService dictService;

    @PostMapping("/DictController_doAdd")
    @ResponseBody
    public ResultMsg doAdd(Dict dict) {
        int result = dictService.insert(dict);
        if (result > 0) {
            return ResultMsg.getSuccess();
        }
        return ResultMsg.getError();
    }

    @PostMapping("/DictController_doEdit")
    @ResponseBody
    public ResultMsg edit(Dict dict) {
        int result = dictService.update(dict);
        if (result > 0) {
            return ResultMsg.getSuccess();
        }
        return ResultMsg.getError();
    }

    @RequestMapping("/DictController_doRemove")
    public ResultMsg remove(String ids) {
        int result = dictService.deleteByIds(ids);
        if (result > 0) {
            return ResultMsg.getSuccess();
        }
        return ResultMsg.getError();
    }
}
