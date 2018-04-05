package com.spilder.interfaces;

import com.spilder.entitys.Novel;

import java.util.List;

/**
 * Created by dingshuangkun on 2018/4/5.
 */
public interface INovelSpider {
    /** 抓取某一个页面时最大的尝试次数3 */
    public static final int MAX_TRY_TIMES = 3;
    /**
     * 给我一个URL，我就个你一堆的小说实体
     * @param url
     * @return
     */
    public List<Novel> getsNovel(String url);
}
