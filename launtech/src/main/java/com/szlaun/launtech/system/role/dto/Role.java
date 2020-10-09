package com.szlaun.launtech.system.role.dto;

import com.szlaun.launtech.utils.BaseEntity;

import java.util.Date;

public class Role extends BaseEntity {
    private String name;

    private String[] menuIds;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(String[] menuIds) {
        this.menuIds = menuIds;
    }
}