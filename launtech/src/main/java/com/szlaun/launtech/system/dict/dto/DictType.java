package com.szlaun.launtech.system.dict.dto;

import com.szlaun.launtech.utils.BaseEntity;



public class DictType extends BaseEntity {
    private String name;

    private String categoryNo;

    public String getCategoryNo() {
        return categoryNo;
    }

    public void setCategoryNo(String categoryNo) {
        this.categoryNo = categoryNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}