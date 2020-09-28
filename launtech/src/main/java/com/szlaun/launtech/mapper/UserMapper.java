package com.szlaun.launtech.mapper;

import com.szlaun.launtech.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    List<User> selectAll();

    int verifyPermission(@Param("userId") String userId, @Param("permissStrs") List<String> permissStrs);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}