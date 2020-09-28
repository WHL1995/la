package com.szlaun.launtech.system.role.mapper;

import com.szlaun.launtech.system.role.dto.RoleMenuKey;

public interface RoleMenuMapper {
    int deleteByPrimaryKey(RoleMenuKey key);

    int insert(RoleMenuKey record);

    int insertSelective(RoleMenuKey record);
}