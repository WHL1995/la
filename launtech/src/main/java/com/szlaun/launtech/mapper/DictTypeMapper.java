package com.szlaun.launtech.mapper;

import com.szlaun.launtech.model.DictType;

public interface DictTypeMapper {
    int deleteByPrimaryKey(String id);

    int insert(DictType record);

    int insertSelective(DictType record);

    DictType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DictType record);

    int updateByPrimaryKey(DictType record);
}