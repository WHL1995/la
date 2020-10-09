package com.szlaun.launtech.system.menu.service.impl;

import com.szlaun.launtech.enums.PropertyAddFlagEnum;
import com.szlaun.launtech.system.menu.dto.Menu;
import com.szlaun.launtech.system.menu.mapper.MenuMapper;
import com.szlaun.launtech.system.menu.service.MenuService;
import com.szlaun.launtech.utils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Description
 * @Author lizhiming
 * @Date 2020/10/9 10:28
 * @Version V1.0
 **/
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public int insert(Menu record) {
        PropertyUtils.addDefaultProperty(record, PropertyAddFlagEnum.INSERT,record.getCreateUser());
        return menuMapper.insertSelective(record);
    }

    @Override
    public int delete(String id) {
        return menuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int update(Menu record) {
        PropertyUtils.addDefaultProperty(record, PropertyAddFlagEnum.UPDATE,record.getUpdateUser());
        return menuMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Menu selectByPrimaryKey(String id) {
        return menuMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Menu> selectMenuByUserId(String userId) {
        return menuMapper.selectMenuByUserId(userId);
    }

    @Override
    public List<Menu> selectButtonByUserId(String userId) {
        return menuMapper.selectButtonByUserId(userId);
    }

    @Override
    public List<Menu> selectButtonByRoleId(String roleId) {
        return menuMapper.selectButtonByRoleId(roleId);
    }

    @Override
    public List<Menu> selectMenuByRoleId(String roleId) {
        return menuMapper.selectMenuByRoleId(roleId);
    }
}
