package com.book.vo;
import com.mysql.model.ChapterDO;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author  dingshuangkun
 * @Date on 2018/4/15.
 */
@Data
public class NovelVO {
    private Long id;

    private String bookName;

    private String url;

    private String author;

    private String lastUpdateChapter;

    private String lastUpdateChapterUrl;

    private String firstLetter;

    private Integer writingState;

    private String addTime;

    private String updateTime;

    private String  bookState;

    private String bookType;

    private String chapters;
}
