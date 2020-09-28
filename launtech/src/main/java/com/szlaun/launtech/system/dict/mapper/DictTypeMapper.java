package com.szlaun.launtech.system.dict.mapper;

import com.szlaun.launtech.system.dict.dto.DictType;

public interface DictTypeMapper {
    int deleteByPrimaryKey(String id);

    int insert(DictType record);

    int insertSelective(DictType record);

    DictType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DictType record);

    int updateByPrimaryKey(DictType record);
}