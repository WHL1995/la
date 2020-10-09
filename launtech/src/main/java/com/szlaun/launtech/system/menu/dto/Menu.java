package com.szlaun.launtech.system.menu.dto;

import com.szlaun.launtech.utils.BaseEntity;

import java.util.Date;

public class Menu extends BaseEntity {
    private String url;

    private String permission;

    private Integer type;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}