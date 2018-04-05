package com.spilder.entitys;

import lombok.Data;

/**
 * Created by dingshuangkun on 2018/3/27.
 */
@Data
public class ChapterDetail {
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 前一章
     */
    private String prev;
    /**
     * 下一章
     */
    private String next;
}
