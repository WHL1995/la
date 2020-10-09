package com.szlaun.launtech.system.role.mapper;

import com.szlaun.launtech.system.role.dto.RoleMenuKey;

import java.util.List;

public interface RoleMenuMapper {
    int deleteByPrimaryKey(RoleMenuKey key);

    int insert(RoleMenuKey record);

    int insertSelective(RoleMenuKey record);

    int batchRoleMenu(List<RoleMenuKey> list);

    int deleteByRoleId(String id);
}