package com.mysql.dao;

import com.mysql.model.ChapterDO;
import com.mysql.query.QueryChapter;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dingshuangkun on 2018/4/12.
 */
@Repository
public interface ChapterDAO {
    /**
     * 插入章节
     * @param chapterDO
     * @return
     */
    Integer insertChapter(ChapterDO chapterDO);

    /**
     * 查询小说章节
     * @param queryChapter
     * @return
     */
    List<ChapterDO> selectChapter(QueryChapter queryChapter);

}
