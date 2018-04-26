package com.book.service.impl;

import com.book.service.ChapterService;
import com.book.service.NovelService;
import com.book.util.ChapterVOAndDO;
import com.book.util.TimeUtil;
import com.book.util.VOAndDO;
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
        VOAndDO<ChapterVO,ChapterDO> vd = new ChapterVOAndDO();
       List<ChapterDO> chapterDOList = chapterDAO.selectChapter(queryChapter);
       List<ChapterVO> chapterVOS = vd.from(chapterDOList);
        return chapterVOS;
    }
}
