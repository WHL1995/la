package com.szlaun.launtech.system.dict.mapper;

import com.szlaun.launtech.system.dict.dto.Dict;

import java.util.List;

public interface DictMapper {
    int deleteByPrimaryKey(String id);

    int insert(Dict record);

    int insertSelective(Dict record);

    Dict selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Dict record);

    int updateByPrimaryKey(Dict record);

    int deleteByIds(String[] ids);

    int selectByDictTypeId(String dictTypeId);

    List<Dict> selectAll();

}