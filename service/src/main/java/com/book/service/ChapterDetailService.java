package com.book.service;
import com.book.vo.ChapterDetailVO;
import com.mysql.model.ChapterDetailDO;

/**
 * Created by dingshuangkun on 2018/4/18.
 */
public interface ChapterDetailService {

    /**
     * 根据id查询章节内容
     * @param id
     * @return
     */
    ChapterDetailVO queryChapterDetail(Long id);

    /**
     * 添加小说章节
     * @param chapterDetailDO
     * @return
     */
    Integer addChapterDetail(ChapterDetailDO chapterDetailDO);
}
