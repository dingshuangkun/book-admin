package com.spilder.util;

import com.spilder.enums.NovelSiteEnum;
import com.spilder.impl.chapter.BxwxChapterSpider;
import com.spilder.impl.chapter.DefaultChapterSpider;
import com.spilder.interfaces.IChapterSpider;

/**
 * @author  dingshuangkun on 2018/4/5.
 */
public class ChapterSpiderFactory {

    private ChapterSpiderFactory() {}

    /**
     * 通过给定的url，返回一个实现了IChapterSpider接口的实现类
     * @param url
     * @return
     */
    public static IChapterSpider getChapterSpider(String url) {
        NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
        IChapterSpider chapterSpider = null;
        switch (novelSiteEnum) {
            case Bxwx :
                chapterSpider = new BxwxChapterSpider(); break;
            case DingDianXiaoShuo:
            case BiQuGe:
            case KanShuZhong :
                chapterSpider = new DefaultChapterSpider(); break;
        }
        return chapterSpider;
    }
}
