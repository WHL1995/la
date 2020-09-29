package com.szlaun.launtech.system.dict.controller;

import com.alibaba.fastjson.JSONObject;
import com.szlaun.launtech.system.dict.dto.DictType;
import com.szlaun.launtech.system.dict.service.DictTypeService;
import com.szlaun.launtech.utils.ResultMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/DictTypeController")
public class DictTypeController {

    @Autowired
    private DictTypeService dictTypeService;

    @PostMapping("/DictTypeController_doAdd")
    @ResponseBody
    public ResultMsg doAdd(DictType dictType) {
        int result = dictTypeService.insert(dictType);
        if (result > 0) {
            return ResultMsg.getSuccess();
        }
        return ResultMsg.getError();
    }

    @PostMapping("/DictTypeController_doEdit")
    @ResponseBody
    public ResultMsg doEdit(DictType dictType) {
        JSONObject jsonObject = new JSONObject();

        int result = dictTypeService.update(dictType);
        if (result > 0) {
            return ResultMsg.getSuccess();
        }
        return ResultMsg.getError();
    }

    @PostMapping("/DictTypeController_doRemove")
    @ResponseBody
    public ResultMsg doRemove(String ids) {
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
