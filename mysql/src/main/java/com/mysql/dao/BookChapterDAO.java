package com.mysql.dao;

import com.mysql.model.BookChapterDO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dingshuangkun on 2018/3/25.
 */
@Repository
public interface BookChapterDAO {
    /**
     * 根据章节查询图书编码
     * @param title
     * @return
     */
    String queryBookCoding(String title);

    /**
     * 根据图书编码查询图书章节
     * @param bookCoding
     * @return
     */
    List<BookChapterDO>queryBookChaters(String bookCoding);
    /**
     * 批量插入图书章节
     * @param bookChapterDOS
     * @return
     */
    Integer batchInsert(List<BookChapterDO> bookChapterDOS);

    /**
     * 插入图书章节
     * @param bookChapterDO
     * @return
     */
    Integer insert(BookChapterDO bookChapterDO);
}
