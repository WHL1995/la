package com.szlaun.launtech.mapper;

import com.szlaun.launtech.model.RoleMenuKey;

public interface RoleMenuMapper {
    int deleteByPrimaryKey(RoleMenuKey key);

    int insert(RoleMenuKey record);

    int insertSelective(RoleMenuKey record);
}