package com.book.util;

import com.book.vo.ChapterVO;
import com.mysql.model.ChapterDO;
import org.springframework.beans.BeanUtils;

/**
 * Created by dingshuangkun on 2018/4/26.
 */
public class ChapterVOAndDO implements VOAndDO<ChapterVO,ChapterDO> {

    @Override
    public ChapterVO from(ChapterDO d) {
        ChapterVO chapterVO = new ChapterVO();
        BeanUtils.copyProperties(d,chapterVO);
        chapterVO.setCreateTime(TimeUtil.formatYYYY_MM_dd(d.getCreateTime()));
        chapterVO.setUpdateTime(TimeUtil.formatYYYY_MM_dd(d.getUpdateTime()));
        return chapterVO;
    }

    @Override
    public ChapterDO to(ChapterVO v) {
        ChapterDO chapterDO = new ChapterDO();
        BeanUtils.copyProperties(v,chapterDO);
        chapterDO.setUpdateTime(TimeUtil.parseYYYY_MM_dd(v.getUpdateTime()));
        chapterDO.setCreateTime(TimeUtil.parseYYYY_MM_dd(v.getCreateTime()));
        return chapterDO;
    }
}
