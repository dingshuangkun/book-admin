package com.mysql.dao;

import com.mysql.model.BookDO;
import com.mysql.query.QueryBook;

import java.util.List;

/**
 * Created by dingshuangkun on 2018/2/7.
 */
public interface BookDAO {
    /**
     * 查询book表
     * @param queryBook
     * @return
     */
     List<BookDO> findBooks(QueryBook queryBook);

    /**
     * 插入BookDO
     * @param bookDO
     * @return
     */
     Boolean insertBookDO(BookDO bookDO);

    /**
     * 批量插入BookDO
     * @param bookDOS
     * @return
     */
     Boolean insertBookDOS(List<BookDO> bookDOS);

    /**
     * 根据Id 批量删除
     * @param ids
     * @return
     */
     Integer deleteByIds(List<Long> ids);

}
