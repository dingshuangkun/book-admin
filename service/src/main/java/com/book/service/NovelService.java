package com.book.service;

import com.book.util.CollectionUtil;
import com.book.vo.NovelVO;
import com.mysql.query.QueryNovel;
import com.redis.service.RedisNovelService;
import com.redis.service.impl.RedisNovelServiceImpl;

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


     List<NovelVO> queryLikeByauthorOrTitle(String authorOrTitle);

    /**
     * 根据id查询
     * @param id
     * @return
     */
    default NovelVO queryNovelById(Long id){

        QueryNovel queryNovel = new QueryNovel();
        queryNovel.setId(id);
        List<NovelVO> novelVOList = queryNovel(queryNovel);
        if(CollectionUtil.isNotEmpty(novelVOList)){
            return novelVOList.get(0);
        }else {
            return null;
        }
    }

    /**
     * 根据书名查询
     * @param bookName
     * @return
     */
    default List<NovelVO> queryNovelByBookName(String bookName){
        QueryNovel queryNovel = new QueryNovel();
        queryNovel.setBookName(bookName);
        return queryNovel(queryNovel);
    }
    /**
     * 根据作者查询小说
     * @param author
     * @return
     */
    default List<NovelVO> queryNovelByAuthor(String author){
        QueryNovel queryNovel = new QueryNovel();
        queryNovel.setAuthor(author);
        return  queryNovel(queryNovel);
    }

    /**
     * 根据作者和状态查询
     * @param author
     * @param state
     * @return
     */
    default List<NovelVO> queryNovelByAuthorAndState(String author,Integer state){
        QueryNovel queryNovel = new QueryNovel();
        queryNovel.setAuthor(author);
        queryNovel.setBookState(state);
        return queryNovel(queryNovel);
    }
}
