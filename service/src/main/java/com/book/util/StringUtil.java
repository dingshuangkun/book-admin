package com.book.util;

/**
 * Created by dingshuangkun on 2018/4/17.
 */
public class StringUtil {

    public static boolean isNotEmpty(String str){
        if(str != null && str.length()>0){
            return true;
        }
        return false;
    }
}
