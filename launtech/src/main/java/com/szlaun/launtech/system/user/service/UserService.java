package com.szlaun.launtech.system.user.service;

import com.szlaun.launtech.system.user.dto.User;

import java.util.List;

public interface UserService {

    int insert(User user);

    User selectById(String id);

    User verifyAccount(String account,String password);

    int verifyPermission(String userId, List<String> permissStrs);

    List<User> selectAll();

    int updateByPrimaryKeySelective(User user);

    int deleteByPrimaryKey(String userId);
}
