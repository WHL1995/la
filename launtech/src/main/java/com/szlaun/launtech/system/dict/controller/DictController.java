package com.szlaun.launtech.system.dict.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.szlaun.launtech.anno.Authority;
import com.szlaun.launtech.system.dict.dto.Dict;
import com.szlaun.launtech.system.dict.service.DictService;
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
    public JSON doAdd(Dict dict) {
        JSONObject json = new JSONObject();
        int result = dictService.insert(dict);
        if (result > 0) {
            json.put("code", 200);
            json.put("msg", "操作成功");
        } else {
            json.put("code", 500);
            json.put("msg", "操作失败");
        }
        return json;
    }

    @PostMapping("/DictController_doEdit")
    @ResponseBody
    public JSON edit(Dict dict) {
        JSONObject jsonObject = new JSONObject();
        int result = dictService.update(dict);
        if (result > 0) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "操作成功");
        } else {
            jsonObject.put("code", 500);
            jsonObject.put("msg", "操作失败");
        }
        return jsonObject;
    }

    @RequestMapping("/DictController_doRemove")
    public JSON remove(String ids) {
        JSONObject jsonObject = new JSONObject();
        int result = dictService.deleteByIds(ids);
        if (result > 0) {
            jsonObject.put("code", 200);
            jsonObject.put("msg", "操作成功");
        } else {
            jsonObject.put("code", 500);
            jsonObject.put("msg", "操作失败");
        }
        return jsonObject;
    }
}
