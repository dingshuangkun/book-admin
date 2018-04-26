package com.test.service;

import com.book.enums.BookState;
import com.book.service.ChapterService;
import com.book.service.NovelService;
import com.book.util.TimeUtil;
import com.book.vo.ChapterVO;
import com.book.vo.NovelVO;
import com.mysql.model.ChapterDO;
import com.mysql.model.NovelDO;
import com.mysql.query.QueryNovel;
import com.redis.service.RedisChapterService;
import com.redis.service.RedisNovelService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dingshuangkun on 2018/4/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext.xml")
public class NovelTest {

    @Autowired
    private NovelService novelService;
    @Autowired
    private ChapterService chapterService;
    @Autowired
    private RedisChapterService redisChapterService;

    @Autowired
    private RedisNovelService redisNovelService;
    @Test
    public void testInsertChapterDOToRedis() {
        for (int bookId = 969; bookId <= 1048; bookId++) {

           List<ChapterVO> chapterVOS  = chapterService.queryChapterByBookId(Long.valueOf(bookId));
           List<ChapterDO> chapterDOS = new ArrayList<>();
           chapterVOS.forEach(n->{
               ChapterDO chapterDO = new ChapterDO();
               BeanUtils.copyProperties(n,chapterDO);
               chapterDO.setCreateTime(TimeUtil.parseYYYY_MM_dd(n.getCreateTime()));
               chapterDO.setUpdateTime(TimeUtil.parseYYYY_MM_dd(n.getUpdateTime()));
               chapterDOS.add(chapterDO);
           });
            redisChapterService.addChapterList(chapterDOS,Long.valueOf(bookId));
            System.out.println("bookId="+bookId);
        }
    }

    @Test
    public void testQuery(){
        List<ChapterDO> chapterDOList = redisChapterService.queryByBookId(1000L);
        System.out.println(chapterDOList);
    }

    @Test
    public void testInsertNovelToRedis(){
        for(int id=996; id<=1048;id++) {
            NovelVO novelVO = novelService.queryNovelById(Long.valueOf(id));
            if(novelVO!=null) {
                NovelDO novelDO = new NovelDO();
                BeanUtils.copyProperties(novelVO, novelDO);
                novelDO.setBookState(BookState.getByDesc(novelVO.getBookState()).getType());
                novelDO.setAddTime(TimeUtil.parseYYYY_MM_dd( novelVO.getAddTime()));
                novelDO.setUpdateTime(TimeUtil.parseYYYY_MM_dd(novelVO.getUpdateTime()));
                redisNovelService.addNovelDO(novelDO);
                System.out.println("id=" + id);
            }
        }

    }

    @Test
    public void testQueryNovel(){
      NovelDO novelDO =  redisNovelService.queryById(1000L);
        System.out.println(novelDO);
    }
    @Test
    public void testQueryNovelAll(){
        redisNovelService.queryAll();
    }
}
