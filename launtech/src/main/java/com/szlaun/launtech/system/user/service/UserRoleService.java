package com.szlaun.launtech.system.user.service;

import com.szlaun.launtech.system.user.dto.UserRoleKey;

/**
 * @Description
 * @Author lizhiming
 * @Date 2020/10/9 10:02
 * @Version V1.0
 **/
public interface UserRoleService {

    int deleteUR(UserRoleKey userRole);

    int insertUR(UserRoleKey userRole);
}
