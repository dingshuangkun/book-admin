package com.mysql.dao;
import com.mysql.model.ChapterDetailDO;
import org.springframework.stereotype.Repository;

/**
 * @author  dingshuangkun
 * @date on 2018/4/14.
 */
@Repository
public interface ChapterDetailDAO {
    /**
     * 插入小说对应的内容
     * @param chapterDetailDO
     * @return
     */
    Integer insertChapterDetail(ChapterDetailDO chapterDetailDO);

    /**
     * 查询小说章节内容
     * @return
     */
    ChapterDetailDO selectChapterDetail(Long id);
}
