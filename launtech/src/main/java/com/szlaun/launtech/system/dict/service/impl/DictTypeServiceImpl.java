package com.szlaun.launtech.system.dict.service.impl;

import com.szlaun.launtech.system.dict.dto.DictType;
import com.szlaun.launtech.system.dict.mapper.DictMapper;
import com.szlaun.launtech.system.dict.mapper.DictTypeMapper;
import com.szlaun.launtech.system.dict.service.DictService;
import com.szlaun.launtech.system.dict.service.DictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class DictTypeServiceImpl implements DictTypeService {
    @Autowired
    private DictTypeMapper dictTypeMapper;

    @Autowired
    private DictMapper dictMapper;

    @Override
    public int insert(DictType dictType) {
        String id = UUID.randomUUID().toString().replace("-", "");
        dictType.setId(id);
        dictType.setCreateTime(new Date());
        int insert = dictTypeMapper.insert(dictType);
        return insert;
    }

    @Override
    public int update(DictType dictType) {
        dictType.setUpdateTime(new Date());
        int i = dictTypeMapper.updateByPrimaryKey(dictType);
        return i;
    }

    @Override
    public int deleteByIds(String ids) throws Exception{
        String[] str = ids.split(",");
        if (str.length > 0) {
            for (String dictTypeId : str) {
                DictType dictType = dictTypeMapper.selectByPrimaryKey(dictTypeId);
                int count = dictMapper.selectByDictTypeId(dictType.getId());
                if(count>0){
                    throw new Exception(String.format("%1$s已分配,不能删除",
                            dictType.getName()));
                }
            }
        }
        return dictTypeMapper.deleteByIds(str);
    }
}
