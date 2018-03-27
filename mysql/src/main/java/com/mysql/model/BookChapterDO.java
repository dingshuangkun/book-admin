package com.mysql.model;

import lombok.Data;

/**
 * Created by dingshuangkun on 2018/3/25.
 */
@Data
public class BookChapterDO {
    /**
     * 主键
     */
    private Long id;
    /**
     * 图书编码
     */
    private String bookCoding;
    /**
     * 章节标题
     */
    private String title;
    /**
     * url
     */
    private String url;
}
