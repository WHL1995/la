package com.szlaun.launtech.system.menu.service;

import com.szlaun.launtech.system.menu.dto.Menu;

import java.util.List;

/**
 * @Description
 * @Author lizhiming
 * @Date 2020/10/9 10:28
 * @Version V1.0
 **/
public interface MenuService {

    int insert(Menu record);

    int delete(String id);

    int update(Menu record);

    Menu selectByPrimaryKey(String id);

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
