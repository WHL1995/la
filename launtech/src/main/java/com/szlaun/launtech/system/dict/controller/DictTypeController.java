package com.szlaun.launtech.system.dict.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.szlaun.launtech.system.dict.dto.DictType;
import com.szlaun.launtech.system.dict.service.DictTypeService;
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
    public JSON doAdd(DictType dictType) {
        JSONObject jsonObject = new JSONObject();

        int result = dictTypeService.insert(dictType);
        if (result > 0) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "操作成功");
        } else {
            jsonObject.put("code",500);
            jsonObject.put("msg","操作失败");
        }
        return jsonObject;
    }

    @PostMapping("/DictTypeController_doEdit")
    @ResponseBody
    public JSON doEdit(DictType dictType) {
        JSONObject jsonObject = new JSONObject();

        int result = dictTypeService.update(dictType);
        if (result > 0) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "操作成功");
        } else {
            jsonObject.put("code",500);
            jsonObject.put("msg","操作失败");
        }
        return jsonObject;
    }

    @PostMapping("/DictTypeController_doRemove")
    @ResponseBody
    public JSON doRemove(String ids) {
        JSONObject jsonObject = new JSONObject();

        int result = 0;
        try {
            result = dictTypeService.deleteByIds(ids);
        } catch (Exception e) {
             jsonObject.put("code",500);
             jsonObject.put("msg",e.getMessage());
             return jsonObject;
        }
        if (result > 0) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "操作成功");
        } else {
            jsonObject.put("code",500);
            jsonObject.put("msg","操作失败");
        }
        return jsonObject;
    }

}
