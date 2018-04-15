package com.mysql.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by dingshuangkun on 2018/4/12.
 */
@Data
public class ChapterDO {
    /**
     * 主键
     */
    private Long id;
    /**
     * 图书的id
     */
    private Long bookId;
    /**
     * 章节标题
     */
    private String title;
    /**
     * url
     */
    private String url;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 修改时间
     */
    private Date updateTime;
}
