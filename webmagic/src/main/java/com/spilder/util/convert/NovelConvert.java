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
        novel.setName(novelDO.getBookname());
        novel.setStatus(novelDO.getBookState());
        novel.setUrl(novelDO.getUrl());
        novel.setAddTime(novelDO.getAddTime());
        return novel;
    }

    @Override
    public NovelDO to(Novel novel) {
        NovelDO novelDO = new NovelDO();
        novelDO.setAddTime(novel.getAddTime());
        novelDO.setAuthor(novel.getAuthor());
        novelDO.setBookname(novel.getName());
        novelDO.setBookState(novel.getStatus());
        novelDO.setUpdateTime(novel.getLastUpdateTime());
        novelDO.setUrl(novel.getUrl());
        return novelDO;
    }
}