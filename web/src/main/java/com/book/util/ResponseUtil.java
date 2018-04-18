package com.book.util;

import lombok.Data;

/**
 * Created by dingshuangkun on 2018/4/18.
 */
@Data
public class ResponseUtil<T> {

    private T t;

    private Integer size;

    private Integer total;

    public ResponseUtil(T t, Integer size, Integer total) {
        this.t = t;
        this.size = size;
        this.total = total;
    }
}
