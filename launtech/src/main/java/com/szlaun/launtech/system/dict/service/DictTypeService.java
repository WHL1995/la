package com.szlaun.launtech.system.dict.service;

import com.szlaun.launtech.system.dict.dto.DictType;

public interface DictTypeService {
    int insert(DictType dictType);

    int update(DictType dictType);

    int deleteByIds(String ids) throws Exception;

    ;
}
