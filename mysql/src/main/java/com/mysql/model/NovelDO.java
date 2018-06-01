package com.mysql.model;

import com.mysql.annotation.RedisQuery;
import lombok.Data;
import java.util.Date;

/**
 * Created by dingshuangkun on 2018/4/7.
 */
@Data
public class NovelDO  {

    @RedisQuery
    private Long id;
    @RedisQuery
    private String bookName;

    private String url;
    @RedisQuery
    private String author;

    private String lastUpdateChapter;

    private String lastUpdateChapterUrl;

    private String firstLetter;

    private Integer writingState;
    @RedisQuery
    private Date addTime;
    @RedisQuery
    private Date updateTime;

    private Integer bookState;

    private String bookType;

    private Integer integration;
}
