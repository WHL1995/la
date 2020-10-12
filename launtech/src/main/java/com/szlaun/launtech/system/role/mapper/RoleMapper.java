package com.szlaun.launtech.system.role.mapper;

import com.szlaun.launtech.system.role.dto.Role;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(String id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    int deleteByIds(String[] split);

    List<Role> selectAll();

}