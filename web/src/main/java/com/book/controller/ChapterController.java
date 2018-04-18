package com.book.controller;

import com.book.service.ChapterDetailService;
import com.book.service.ChapterService;
import com.book.util.ResponseUtil;
import com.book.vo.ChapterDetailVO;
import com.book.vo.ChapterVO;
import com.mysql.query.QueryChapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author  dingshuangkun
 * @date on 2018/4/17
 */
@Controller
@RequestMapping("/chapter/")
public class ChapterController {

    @Autowired
    private ChapterService chapterService;
    @Autowired
    private ChapterDetailService chapterDetailService;

    @ResponseBody
    @CrossOrigin(origins = "*" ,maxAge = 360)
    @RequestMapping("query")
    public List<ChapterVO> queryChapter(){
        QueryChapter queryChapter = new QueryChapter();
        queryChapter.setBeginIndex(0);
        queryChapter.setEndIndex(20);
        return chapterService.queryChapter(queryChapter);
    }

    @ResponseBody
    @CrossOrigin(origins = "*" ,maxAge = 360)
    @RequestMapping("detail/query")
    public ChapterDetailVO queryDetail(@RequestParam("id") Long id) {
       return   chapterDetailService.queryChapterDetail(id);
    }
}
