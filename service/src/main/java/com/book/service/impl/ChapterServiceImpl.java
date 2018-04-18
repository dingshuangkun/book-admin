package com.book.service.impl;

import com.book.service.ChapterService;
import com.book.service.NovelService;
import com.book.util.TimeUtil;
import com.book.vo.ChapterVO;
import com.book.vo.NovelVO;
import com.mysql.dao.ChapterDAO;
import com.mysql.model.ChapterDO;
import com.mysql.query.QueryChapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dingshuangkun on 2018/4/17.
 */
@Service
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterDAO chapterDAO;
    @Autowired
    private NovelService novelService;
    @Override
    public List<ChapterVO> queryChapter(QueryChapter queryChapter) {
       List<ChapterDO> chapterDOList = chapterDAO.selectChapter(queryChapter);
       List<ChapterVO> chapterVOS = new ArrayList<>();
       chapterDOList.forEach(n->{
           ChapterVO chapterVO = new ChapterVO();
           NovelVO novelVO = novelService.queryNovelById(n.getBookId());
           chapterVO.setBookId(n.getBookId());
           chapterVO.setBookName(novelVO.getBookName());
           chapterVO.setId(n.getId());
           chapterVO.setUrl(n.getUrl());
           chapterVO.setTitle(n.getTitle());
           chapterVO.setUpdateTime(TimeUtil.formatYYYY_MM_dd(n.getUpdateTime()));
           chapterVO.setCreateTime(TimeUtil.formatYYYY_MM_dd(n.getCreateTime()));
           chapterVOS.add(chapterVO);
       });
        return chapterVOS;
    }
}
