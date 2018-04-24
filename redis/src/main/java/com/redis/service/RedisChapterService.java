package com.redis.service;

import com.mysql.model.ChapterDO;

import java.util.List;

/**
 * @author  dingshuangkun
 * @date on 2018/4/24.
 */
public interface RedisChapterService {
    /**
     * 根据bookId 查询
     * @param bookId
     * @return
     */
    List<ChapterDO> queryByBookId(Long  bookId);

    /**
     * 根据id 查询
     * @param id
     * @return
     */
    ChapterDO queryById(Long id);


    void addChapterList(List<ChapterDO> chapterDOList,Long bookId);


}
