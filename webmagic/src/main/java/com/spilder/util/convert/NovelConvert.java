package com.spilder.util.convert;

import com.mysql.model.NovelDO;
import com.spilder.entitys.Novel;

/**
 * Created by dingshuangkun on 2018/4/7.
 */
public class NovelConvert implements ConvertUtil<Novel,NovelDO> {

    @Override
    public  Novel from(NovelDO novelDO){
        Novel novel = new Novel();
        novel.setAuthor(novelDO.getAuthor());
        novel.setLastUpdateChapter(novelDO.getLastUpdateChapter());
        novel.setLastUpdateTime(novelDO.getUpdateTime());
        novel.setLastUpdateChapterUrl(novelDO.getLastUpdateChapterUrl());
        novel.setName(novelDO.getBookName());
        novel.setStatus(novelDO.getBookState());
        novel.setUrl(novelDO.getUrl());
        novel.setAddTime(novelDO.getAddTime());
        return novel;
    }

    @Override
    public NovelDO to(Novel novel) {
        NovelDO novelDO = new NovelDO();

        novelDO.setAuthor(novel.getAuthor());
        novelDO.setBookName(novel.getName());
        novelDO.setBookState(novel.getStatus());
        novelDO.setUpdateTime(novel.getLastUpdateTime());
        novelDO.setUrl(novel.getUrl());
        novelDO.setLastUpdateChapter(novel.getLastUpdateChapter());
        novelDO.setLastUpdateChapterUrl(novel.getLastUpdateChapterUrl());
        novelDO.setBookState(novel.getStatus());
        novelDO.setBookType(novel.getType());
        novelDO.setFirstLetter(novel.getFirstLetter()+"");
        return novelDO;
    }
}