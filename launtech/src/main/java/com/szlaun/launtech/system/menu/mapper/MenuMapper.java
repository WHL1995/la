package com.szlaun.launtech.system.menu.mapper;

import com.szlaun.launtech.system.menu.dto.Menu;

import java.util.List;

public interface MenuMapper {
    int deleteByPrimaryKey(String id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    /**
     * 查询用户所有的菜单
     * @param userId
     * @return
     */
    List<Menu> selectMenuByUserId(String userId);

    /**
     * 查询用户所有的按钮
     * @param userId
     * @return
     */
    List<Menu> selectButtonByUserId(String userId);

    /**
     * 查询角色所有的按钮
     * @param roleId
     * @return
     */
    List<Menu> selectButtonByRoleId(String roleId);

    /**
     * 查询角色所有的菜单
     * @param roleId
     * @return
     */
    List<Menu> selectMenuByRoleId(String roleId);
}