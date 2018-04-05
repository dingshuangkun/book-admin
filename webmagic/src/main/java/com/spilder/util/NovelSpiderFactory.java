package com.spilder.util;

import com.spilder.enums.NovelSiteEnum;
import com.spilder.impl.novel.BxwxNovelSpider;
import com.spilder.impl.novel.KanShuZhongNovelSpider;
import com.spilder.interfaces.INovelSpider;

/**
 * Created by dingshuangkun on 2018/4/5.
 */
public class NovelSpiderFactory {

    private NovelSpiderFactory() {}
    public static INovelSpider getNovelSpider(String url) {
        NovelSiteEnum novelSiteEnum = NovelSiteEnum.getEnumByUrl(url);
        switch (novelSiteEnum) {
            case Bxwx : return new BxwxNovelSpider();
            case KanShuZhong : return new KanShuZhongNovelSpider();
            default : throw new RuntimeException(url + "暂时不被支持");
        }
    }

}
