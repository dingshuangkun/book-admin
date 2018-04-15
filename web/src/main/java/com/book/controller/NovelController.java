package com.book.controller;

/**
 * Created by dingshuangkun on 2018/4/12.
 */

import com.book.service.NovelService;
import com.book.vo.NovelVO;
import com.mysql.query.QueryNovel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/novel")
public class NovelController {
    @Autowired
    private NovelService novelService;

    @RequestMapping("/insertChapter")
    @ResponseBody
    public void test(){
        novelService.insertChapter();
    }

    @RequestMapping("/insertContent")
    @ResponseBody
    public String testInsertContent(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                novelService.insertContent();
            }
        }).start();
        return "a";
    }

    @ResponseBody
    @RequestMapping("/query")
    public List<NovelVO> getNovel(){
        QueryNovel queryNovel = new QueryNovel();
        queryNovel.setId(1000L);
        queryNovel.setBeginIndex(0);
        queryNovel.setEndIndex(100);
       return novelService.queryNovel(queryNovel);
    }
}
