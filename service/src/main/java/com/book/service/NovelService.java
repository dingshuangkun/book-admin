package com.book.service;

import com.book.vo.NovelVO;
import com.mysql.query.QueryNovel;

import java.util.List;

/**
 * Created by dingshuangkun on 2018/4/12.
 */
public interface NovelService {
    /**
     * 插入小说
     *
     * @return
     */
    Integer insertNovel();

    /**
     * 插入章节
     *
     * @return
     */
    Integer insertChapter();

    /**
     * 插入内容
     *
     * @return
     */
    Integer insertContent();

    /**
     * 根据参数查询小说
     * @param queryNovel
     * @return
     */
     List<NovelVO> queryNovel(QueryNovel queryNovel);
}
