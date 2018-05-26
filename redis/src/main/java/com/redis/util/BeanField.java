package com.redis.util;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Field;

/**
 * Created by dingshuangkun on 2018/4/23.
 */
@Data
@AllArgsConstructor
public class BeanField {
    String primaryKey;
    String className;
    Field[] fieldList;

    //使用lombok的@AllArgsConstructor注解，可以实现这个全参数方法
//    public BeanField(String primaryKey, String className, Field[] fieldList) {
//        this.primaryKey = primaryKey;
//        this.className = className;
//        this.fieldList = fieldList;
//    }
}
