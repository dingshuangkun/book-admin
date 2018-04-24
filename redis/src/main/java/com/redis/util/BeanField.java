package com.redis.util;

import lombok.Data;

import java.lang.reflect.Field;

/**
 * Created by dingshuangkun on 2018/4/23.
 */
@Data
public class BeanField {

    String primaryKey;
    String className;
    Field[] fieldList;

    public BeanField(String primaryKey, String className, Field[] fieldList) {
        this.primaryKey = primaryKey;
        this.className = className;
        this.fieldList = fieldList;
    }
}
