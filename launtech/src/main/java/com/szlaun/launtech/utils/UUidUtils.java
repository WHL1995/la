package com.szlaun.launtech.utils;

import java.util.UUID;

/*
 * 生成32位id
 * */
public class UUidUtils {
    public static String getUUid32() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
