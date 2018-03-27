package com.spilder.interfaces;

import com.spilder.entitys.ChapterDetail;

/**
 * Created by dingshuangkun on 2018/3/27.
 */
public interface IChapterDetailSpider {
    /**
     * 给我一个url，我就给你一个对应网站的章节内容实体
     * @param url
     * @return
     */
    public ChapterDetail getChapterDetail(String url);
}
