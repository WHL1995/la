package com.szlaun.launtech.system.user.service.impl;


import com.szlaun.launtech.enums.PropertyAddFlagEnum;
import com.szlaun.launtech.system.user.dto.User;
import com.szlaun.launtech.system.user.mapper.UserMapper;
import com.szlaun.launtech.system.user.service.UserService;
import com.szlaun.launtech.utils.Base64Util;
import com.szlaun.launtech.utils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User selectById(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public User verifyAccount(String account, String password) {
        String pwd = Base64Util.encode(password);
        return userMapper.verifyAccount(account,pwd);
    }

    @Override
    public int verifyPermission(String userId, List<String> permissStrs) {
        return userMapper.verifyPermission(userId,permissStrs);
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKeySelective(User user) {
        user.setPassword(Base64Util.encode(user.getPassword()));
        PropertyUtils.addDefaultProperty(user, PropertyAddFlagEnum.UPDATE,user.getId());
        return userMapper.updateByPrimaryKeySelective(user);
    }
}
