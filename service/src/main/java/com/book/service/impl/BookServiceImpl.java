package com.book.service.impl;

import com.book.service.BookService;
import com.mysql.dao.BookDAO;
import com.mysql.model.BookDO;
import com.mysql.query.QueryBook;
import com.redis.service.RedisNovelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author  dingshuangkun
 * on 2018/2/7.
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookDAO bookDAO;
    @Autowired
    private RedisNovelService redisNovelService;
    public BookDO findById(Long id) {
        // 先从缓存中找

        List<Long> list = new ArrayList<>();
        list.add(id);
        List<BookDO> bookDOS = findByIds(list);
        if (bookDOS == null || bookDOS.isEmpty()) {
            return null;
        }

        return bookDOS.get(0);
    }

    public List<BookDO> findByIds(List<Long> ids) {
        QueryBook queryBook = new QueryBook();
        queryBook.setIds(ids);
        return bookDAO.findBooks(queryBook);
    }

    public BookDO findByBookCoding(String bookCoding) {
        List<String> list = new ArrayList<>();
        list.add(bookCoding);
        List<BookDO> bookDOS = findByBookCodings(list);
        if (bookDOS != null && bookDOS.size() > 0) {
            return bookDOS.get(0);
        } else {
            return null;
        }
    }

    public List<BookDO> findByBookCodings(List<String> bookCodings) {
        QueryBook queryBook = new QueryBook();
        queryBook.setBookCodings(bookCodings);
        return bookDAO.findBooks(queryBook);
    }

    public Boolean insertBookDO(BookDO bookDO) {

        return bookDAO.insertBookDO(bookDO);
    }

    public Boolean batchInsertBookDO(List<BookDO> bookDOS) {

        return bookDAO.insertBookDOS(bookDOS);
    }

    public Boolean deleteById(Long id) {
        List<Long> list = new ArrayList<>();
        list.add(id);
        return batchDeleteById(list);
    }

    public Boolean batchDeleteById(List<Long> ids) {
        return bookDAO.deleteByIds(ids) > 0;
    }
}
