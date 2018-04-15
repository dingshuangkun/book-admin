package com.mysql.dao;

import com.mysql.model.ChapterDO;
import com.mysql.model.ChapterDetailDO;
import org.springframework.stereotype.Repository;

/**
 * Created by dingshuangkun on 2018/4/14.
 */
@Repository
public interface ChapterDetailDAO {
    /**
     * 插入小说对应的内容
     * @param chapterDetailDO
     * @return
     */
    Integer insertChapterDetail(ChapterDetailDO chapterDetailDO);
}
