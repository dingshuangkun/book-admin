package com.mysql.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by dingshuangkun on 2018/4/7.
 */
@Data
public class NovelDO {

    private Long id;

    private String bookname;

    private String url;

    private String author;

    private String lastUpdateChapter;

    private String lastUpdateChapterUrl;

    private String firstLetter;

    private Integer writingState;

    private Date addTime;

    private Date updateTime;

    private Integer bookState;
}
