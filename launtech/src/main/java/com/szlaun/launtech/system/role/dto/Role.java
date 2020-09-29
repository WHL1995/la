package com.szlaun.launtech.system.role.dto;

import com.szlaun.launtech.utils.BaseEntity;

import java.util.Date;

public class Role  extends BaseEntity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}