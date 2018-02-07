package com.book.service;

import com.mysql.model.BookDO;

/**
 * Created by dingshuangkun on 2018/2/7.
 */
public interface BookService {

    public BookDO  findById(Long id);
}
