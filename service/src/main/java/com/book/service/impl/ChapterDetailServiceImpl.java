package com.book.service.impl;

import com.book.service.ChapterDetailService;
import com.book.vo.ChapterDetailVO;
import com.mysql.dao.ChapterDetailDAO;
import com.mysql.model.ChapterDetailDO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author  dingshuangkun
 * @data on 2018/4/18.
 */
@Service
public class ChapterDetailServiceImpl implements ChapterDetailService {
    @Autowired
    private ChapterDetailDAO chapterDetailDAO;
    @Override
    public ChapterDetailVO queryChapterDetail(Long id) {
        ChapterDetailDO chapterDetailDO = chapterDetailDAO.selectChapterDetail(id);
        ChapterDetailVO chapterDetailVO = new ChapterDetailVO();
        BeanUtils.copyProperties(chapterDetailDO,chapterDetailVO);
        return chapterDetailVO;
    }

    @Override
    public Integer addChapterDetail(ChapterDetailDO chapterDetailDO) {
        return null;
    }
}
