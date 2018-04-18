package com.book.vo;

import lombok.Data;

/**
 * Created by dingshuangkun on 2018/4/17.
 */
@Data
public class ChapterVO {
    /**
     * 主键id
     */
    private Long id;
    /**
     * bookId
     */
    private Long bookId;
    /**
     * 图书的名字
     */
    private String bookName;
    /**
     * 章节标题
     */
    private String title;
    /**
     * 对应的url
     */
    private String url;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 修改时间
     */
    private String updateTime;
}
