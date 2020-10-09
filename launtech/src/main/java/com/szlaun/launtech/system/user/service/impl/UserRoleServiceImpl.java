package com.szlaun.launtech.system.user.service.impl;

import com.szlaun.launtech.enums.PropertyAddFlagEnum;
import com.szlaun.launtech.system.user.dto.UserRoleKey;
import com.szlaun.launtech.system.user.mapper.UserRoleMapper;
import com.szlaun.launtech.system.user.service.UserRoleService;
import com.szlaun.launtech.utils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description
 * @Author lizhiming
 * @Date 2020/10/9 10:05
 * @Version V1.0
 **/
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public int deleteUR(UserRoleKey userRole) {
        return userRoleMapper.deleteByPrimaryKey(userRole);
    }

    @Override
    public int insertUR(UserRoleKey userRole) {
        PropertyUtils.addDefaultProperty(userRole, PropertyAddFlagEnum.INSERT,userRole.getCreateUser());
        return userRoleMapper.insertSelective(userRole);
    }
}
