package com.test.service;

import com.book.service.NovelService;
import com.book.vo.NovelVO;
import com.mysql.query.QueryNovel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by dingshuangkun on 2018/4/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext.xml")
public class NovelTest {

    @Autowired
    NovelService novelService;

    @Test
    public void test(){
        QueryNovel queryNovel = new QueryNovel();
        queryNovel.setId(1002L);
        queryNovel.setQueryChapters(true);
        List<NovelVO> list =  novelService.queryNovel(queryNovel);

        System.out.println(list);
    }
}
