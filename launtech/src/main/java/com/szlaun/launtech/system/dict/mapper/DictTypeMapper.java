package com.szlaun.launtech.system.dict.mapper;

import com.szlaun.launtech.system.dict.dto.DictType;

import java.util.List;

public interface DictTypeMapper {
    int deleteByPrimaryKey(String id);

    int insert(DictType record);

    int insertSelective(DictType record);

    DictType selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(DictType record);

    int updateByPrimaryKey(DictType record);

    int deleteByIds(String[] ids);

    List<DictType> selectAll();

}