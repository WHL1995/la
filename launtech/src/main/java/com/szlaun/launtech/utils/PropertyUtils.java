/**
 * <p>Title: PropertyUtils.java</p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: www.douples.com</p>
 *
 * @author hexuefeng
 * @date 2017-12-26
 * @version 1.0
 */
package com.szlaun.launtech.utils;



import com.szlaun.launtech.enums.PropertyAddFlagEnum;

import java.util.Date;

/**
 * 添加公共属性
 * <p>Title: PropertyUtils</p>
 * <p>Description: </p>
 *
 * @author hexuefeng
 * @date 2017-12-26
 */
public class PropertyUtils {

    /**
     * 添加默认属性
     * <p>Title: addDefaultProperty</p>
     * <p>Description: </p>
     *
     * @param entity       添加实体
     * @param methodName   添加的方式  （主要是添加和修改两种）
     * @param operatorId   操作员ID
     * @param operatorName 操作员真实姓名
     */
    public static void addDefaultProperty(BaseEntity entity, PropertyAddFlagEnum methodName, String operatorId, String operatorName) {
        Date date = new Date();
        String now = DateUtils.parseDateToStr(DateUtils.YYYY_MM_DD_HH_MM_SS, date);
        if (PropertyAddFlagEnum.INSERT.equals(methodName)) {
            entity.setCreateDate(now);
            entity.setCreateTime(date);
            entity.setCreateUserId(operatorId);
            entity.setCreateUserName(operatorName);
        } else if (PropertyAddFlagEnum.UPDATE.equals(methodName)) {
            entity.setUpdateDate(now);
            entity.setUpdateTime(date);
            entity.setUpdateUserId(operatorId);
            entity.setUpdateUserName(operatorName);
        }
    }
}
