package com.szlaun.launtech.system.dict.dto;

import com.szlaun.launtech.utils.BaseEntity;

import java.util.Date;

public class Dict extends BaseEntity {


    private String dictTypeId;

    private String name;

    private String value;

    public String getDictTypeId() {
        return dictTypeId;
    }

    public void setDictTypeId(String dictTypeId) {
        this.dictTypeId = dictTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}