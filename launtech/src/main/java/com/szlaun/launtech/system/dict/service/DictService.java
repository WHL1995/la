package com.szlaun.launtech.system.dict.service;

import com.szlaun.launtech.system.dict.dto.Dict;

import java.util.List;

public interface DictService {
    int insert(Dict dict);

    int update(Dict dict);

    int deleteByIds(String ids);

    List<Dict> selectAll();

}
