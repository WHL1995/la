package com.szlaun.launtech.service;

import com.szlaun.launtech.model.User;

import java.util.List;

public interface UserService {

    int insert(User user);

    User selectById(String id);

    int verifyPermission(String userId,List<String> permissStrs);

    List<User> selectAll();
}
