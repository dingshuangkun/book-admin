package com.book.service;

import com.mysql.model.BookDO;

import java.util.List;

/**
 * Created by dingshuangkun on 2018/2/7.
 */
public interface BookService {
    /**
     * 根据Id 查询
     * @param id
     * @return
     */
     BookDO  findById(Long id);

    /**
     * 根据id的集合查询
     * @param ids
     * @return
     */
     List<BookDO> findByIds(List<Long> ids);

    /**
     * 根据图书编码查询
     * @param bookCoding
     * @return
     */
     BookDO findByBookCoding(String bookCoding);

    /**
     * 根据图书编码的集合查询
     * @param bookCodings
     * @return
     */
     List<BookDO> findByBookCodings(List<String> bookCodings);

    /**
     * 插入bookDO
     * @param bookDO
     * @return
     */
     Boolean insertBookDO(BookDO bookDO);

    /**
     * 批量插入
     * @param bookDOS
     * @return
     */
     Boolean batchInsertBookDO(List<BookDO> bookDOS);

    /**
     * 根据Id 删除
     * @param id
     * @return
     */
     Boolean deleteById(Long id);

    /**
     * 根据Id 批量删除
     * @param ids
     * @return
     */
     Boolean batchDeleteById(List<Long> ids);
}
