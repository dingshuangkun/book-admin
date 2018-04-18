package com.book.controller;

/**
 * Created by dingshuangkun on 2018/4/12.
 */

import com.book.service.NovelService;
import com.book.vo.NovelVO;
import com.mysql.query.QueryNovel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @CrossOrigin(origins = "*" ,maxAge = 360)
    @RequestMapping("/query")
    public List<NovelVO> getNovel(QueryNovel queryNovel){
        queryNovel.setBeginIndex(0);
        queryNovel.setEndIndex(20);
       return novelService.queryNovel(queryNovel);
    }

    @ResponseBody
    @CrossOrigin(origins = "*" ,maxAge = 360)
    @RequestMapping("/query/ByAuthorOrTitle")
    public List<NovelVO> getNovel(@RequestParam("authorOrTitle") String authorOrTitle){
        if(StringUtils.isEmpty(authorOrTitle)){
            throw new RuntimeException("titleOrAuthor is null ");
        }
        return novelService.queryLikeByauthorOrTitle(authorOrTitle);
    }

}
