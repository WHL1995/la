package com.szlaun.launtech.system.dict.service.impl;

import com.szlaun.launtech.enums.PropertyAddFlagEnum;
import com.szlaun.launtech.system.dict.dto.Dict;

import com.szlaun.launtech.system.dict.mapper.DictMapper;

import com.szlaun.launtech.system.dict.service.DictService;
import com.szlaun.launtech.utils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.UUID;

@Service
public class DictServiceImpl implements DictService {
    @Autowired
    private DictMapper dictMapper;

    @Override
    public int insert(Dict dict) {

        PropertyUtils.addDefaultProperty(dict, PropertyAddFlagEnum.INSERT, dict.getCreateUser());
        int insert = dictMapper.insert(dict);
        return insert;
    }

    @Override
    public int update(Dict dict) {
        PropertyUtils.addDefaultProperty(dict, PropertyAddFlagEnum.UPDATE, dict.getCreateUser());
        int result = dictMapper.updateByPrimaryKey(dict);
        return result;
    }

    @Override
    public int deleteByIds(String ids) {
        int result = 0;
        String[] str = ids.split(",");
        if (ids.length() > 0) {
            result = dictMapper.deleteByIds(str);
        }
        return result;
    }


}
