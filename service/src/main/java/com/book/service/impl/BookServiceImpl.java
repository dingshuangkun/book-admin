package com.book.service.impl;

import com.book.service.BookService;
import com.mysql.dao.BookDao;
import com.mysql.model.BookDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by dingshuangkun on 2018/2/7.
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDao bookDao;

    @Override
    public BookDO findById(Long id) {
        return bookDao.findById(id);
    }
}
