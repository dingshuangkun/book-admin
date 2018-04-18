package com.book.util;


import java.util.Collection;

/**
 * Created by dingshuangkun on 2018/4/17.
 */
public class CollectionUtil {

    public static boolean isNotEmpty(Collection collection){
        if(collection != null && collection.size()>0){
            return true;
        }
        return false;
    }
}
