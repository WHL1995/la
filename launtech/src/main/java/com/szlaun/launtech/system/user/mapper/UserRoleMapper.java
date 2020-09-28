package com.szlaun.launtech.system.user.mapper;

import com.szlaun.launtech.system.user.dto.UserRoleKey;

public interface UserRoleMapper {
    int deleteByPrimaryKey(UserRoleKey key);

    int insert(UserRoleKey record);

    int insertSelective(UserRoleKey record);
}