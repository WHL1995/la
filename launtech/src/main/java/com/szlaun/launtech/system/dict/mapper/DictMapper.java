package com.szlaun.launtech.system.dict.mapper;

import com.szlaun.launtech.system.dict.dto.Dict;

public interface DictMapper {
    int deleteByPrimaryKey(String id);

    int insert(Dict record);

    int insertSelective(Dict record);

    Dict selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Dict record);

    int updateByPrimaryKey(Dict record);
}