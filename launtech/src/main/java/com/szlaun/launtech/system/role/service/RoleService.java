package com.szlaun.launtech.system.role.service;

import com.szlaun.launtech.system.role.dto.Role;

public interface RoleService {
    int insert(Role role);

    int update(Role role);

    int delete(String ids) throws Exception;
}
