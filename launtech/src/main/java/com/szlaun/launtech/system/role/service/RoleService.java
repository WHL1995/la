package com.szlaun.launtech.system.role.service;

import com.szlaun.launtech.system.role.dto.Role;

import java.util.List;

public interface RoleService {
    int insert(Role role);

    int update(Role role);

    int delete(String ids) throws Exception;

    List<Role> selectAll();

}
