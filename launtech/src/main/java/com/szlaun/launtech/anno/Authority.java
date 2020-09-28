package com.szlaun.launtech.anno;

import java.lang.annotation.*;

/**
 * @Description 权限检查
 * @Author lizhiming
 * @Date 2020/9/5 10:26
 * @Version V1.0
 **/
@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Authority {

    String[] value();

    boolean isAnd() default true;

}
