package com.spilder.entitys;

import lombok.Data;

/**
 * Created by dingshuangkun on 2018/4/7.
 */
@Data
public class BxwxBook extends Book{
    private String lastUpdateChapter;
    private String lastUpdateChapterUrl;
    private String author;
    private String size;
    private String lastUpdateTime;
    private String status;
}
