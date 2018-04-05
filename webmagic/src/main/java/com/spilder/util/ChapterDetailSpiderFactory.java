package com.spilder.util;

import com.spilder.enums.NovelSiteEnum;
import com.spilder.impl.chapter.DefaultChapterDetailSpider;
import com.spilder.interfaces.IChapterDetailSpider;

/**
 * @author  dingshuangkun on 2018/4/5.
 */
public class ChapterDetailSpiderFactory {
    private ChapterDetailSpiderFactory() {}
    /**
     * 通过给定的url拿到实现了IChapterDetailSpider的具体实现类
     * @param url
     * @return
     */
    public static IChapterDetailSpider getChapterDetailSpider(String url) {
        IChapterDetailSpider spider = null;
        NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
        switch (novelSiteEnum) {
            case DingDianXiaoShuo :
            case BiQuGe :
            case KanShuZhong :
            case Bxwx :
                spider = new DefaultChapterDetailSpider();
                break;
        }
        return spider;
    }
}
