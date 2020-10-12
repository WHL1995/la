package com.szlaun.launtech.system.role.service.impl;

import com.szlaun.launtech.enums.PropertyAddFlagEnum;
import com.szlaun.launtech.system.role.dto.Role;
import com.szlaun.launtech.system.role.dto.RoleMenuKey;
import com.szlaun.launtech.system.role.mapper.RoleMapper;
import com.szlaun.launtech.system.role.mapper.RoleMenuMapper;
import com.szlaun.launtech.system.role.service.RoleService;
import com.szlaun.launtech.system.user.dto.UserRoleKey;
import com.szlaun.launtech.system.user.mapper.UserRoleMapper;
import com.szlaun.launtech.utils.PropertyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public int insert(Role role) {
        PropertyUtils.addDefaultProperty(role, PropertyAddFlagEnum.INSERT, role.getCreateUser());
        roleMapper.insert(role);
        return insertRoleMenu(role);
    }

    private int insertRoleMenu(Role role) {
        int result = 1;
        List<RoleMenuKey> menuKeyArrayList = new ArrayList<>();
        String[] menuIds = role.getMenuIds();
        for (String menuId : menuIds) {
            RoleMenuKey roleMenuKey = new RoleMenuKey();
            roleMenuKey.setMenuId(menuId);
            roleMenuKey.setRoleId(role.getId());
            menuKeyArrayList.add(roleMenuKey);
        }
        if (menuKeyArrayList.size() > 0) {
            result = roleMenuMapper.batchRoleMenu(menuKeyArrayList);
        }
        return result;
    }

    @Override
    public int update(Role role) {
        PropertyUtils.addDefaultProperty(role, PropertyAddFlagEnum.UPDATE, role.getCreateUser());
        roleMapper.updateByPrimaryKey(role);
        roleMenuMapper.deleteByRoleId(role.getId());
        return insertRoleMenu(role);
    }

    @Override
    public int delete(String ids) throws Exception {
        String[] split = ids.split(",");
        for (String id : split) {
            Role role = roleMapper.selectByPrimaryKey(id);
            int result = userRoleMapper.selectUserRoleByRoleId(role.getId());
            if(result>0){
                throw new Exception(String.format("%1$s已分配,不能删除",role.getName()));
            }
        }
        return roleMapper.deleteByIds(split);
    }

    @Override
    public List<Role> selectAll() {
        return roleMapper.selectAll();
    }

}
