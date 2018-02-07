package com.book;

import com.book.service.BookService;
import com.mysql.dao.BookDao;
import com.mysql.model.BookDO;

/**
 * Created by dingshuangkun on 2018/2/7.
 */
public class BookServiceImpl implements BookService {

    private BookDao bookDao;
    @Override
    public BookDO findById(Long id) {

        return bookDao.findById(id);
    }
}
