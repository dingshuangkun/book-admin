package com.book.service;

import com.book.util.CollectionUtil;
import com.book.vo.ChapterVO;
import com.mysql.query.QueryChapter;

import java.util.List;

/**
 * @author  dingshuangkun
 * @date on 2018/4/17.
 */
public interface ChapterService {
    /**
     * 查询章节列表
     * @return
     */
    List<ChapterVO>   queryChapter(QueryChapter queryChapter);

    /**
     * 根据Id 查询
     * @param id
     * @return
     */
    default ChapterVO queryChapterById(Long id){
        QueryChapter queryChapter = new QueryChapter();
        queryChapter.setId(id);
        List<ChapterVO> chapterVOS = queryChapter(queryChapter);
       if(CollectionUtil.isNotEmpty(chapterVOS)){
           return chapterVOS.get(0);
       }
       return null;
    }

    /**
     * 根据章节标题查询
     * @param title
     * @return
     */
   default List<ChapterVO> queryChapterByTitle(String title){
       QueryChapter queryChapter = new QueryChapter();
       queryChapter.setTitle(title);
       List<ChapterVO> chapterVOS = queryChapter(queryChapter);
       return chapterVOS;
   }

    /**
     * 根据bookId 查询
     * @param bookId
     * @return
     */
   default List<ChapterVO> queryChapterByBookId(Long bookId){
     QueryChapter queryChapter = new QueryChapter();
     queryChapter.setBookId(bookId);
     return queryChapter(queryChapter);
   }

}
