package com.szlaun.launtech.system.dict.service;

import com.szlaun.launtech.system.dict.dto.Dict;

public interface DictService {
    int insert(Dict dict);

    int update(Dict dict);

    int deleteByIds(String ids);
}
