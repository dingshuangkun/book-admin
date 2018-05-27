package com.redis.service;

import com.mysql.model.NovelDO;

import java.util.List;

/**
 * @author  dingshuangkun
 * @date on 2018/4/21.
 */
public interface RedisNovelService {

    List<NovelDO> queryAll();

    /**
     *根据id查询
     * @return
     */
    NovelDO queryById(Long id);

    /**
     * 根据书名查询
     * @param title
     * @return
     */
    NovelDO queryByTitle(String title);

    /**
     * 根据作者查询
     * @param author
     * @return
     */
    NovelDO queryByAuthor(String author);

    /**
     * 添加小说
     * @param novelDO
     * @return
     */
    void addNovelDO(NovelDO novelDO);
}
