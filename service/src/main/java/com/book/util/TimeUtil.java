package com.book.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dingshuangkun on 2018/4/17.
 */
public class TimeUtil {

    public static String formatYYYY_MM_dd(Date date){
        SimpleDateFormat format = new SimpleDateFormat("YYYY-MM-dd");
        return format.format(date);
    }
}
