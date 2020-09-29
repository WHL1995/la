package com.szlaun.launtech.system.user.service.impl;



import com.szlaun.launtech.system.user.dto.User;
import com.szlaun.launtech.system.user.mapper.UserMapper;
import com.szlaun.launtech.system.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public int verifyPermission(String userId, List<String> permissStrs) {
        return userMapper.verifyPermission(userId,permissStrs);
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }
}
