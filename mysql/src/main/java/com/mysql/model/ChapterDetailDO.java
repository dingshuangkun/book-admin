package com.mysql.model;

import lombok.Data;

/**
 * Created by dingshuangkun on 2018/4/14.
 */
@Data
public class ChapterDetailDO {
    /**
     * 主键（对应chapter_id）
     */
    private Long id;
    /**
     * 章节标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 前一章节的id
     */
    private Long prevId;
    /**
     * 后一章节的id
     */
    private Long nextId;
    /**
     * 前一章节的url
     */
    private String prevUrl;
    /**
     * 后一章节的url
     */
    private String nextUrl;
}
