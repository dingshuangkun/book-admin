package com.book.vo;
import com.mysql.annotation.RedisQuery;
import lombok.Data;
import java.util.List;

/**
 * @author  dingshuangkun
 * @Date on 2018/4/15.
 */
@Data
public class NovelVO {
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

    private String addTime;

    private String updateTime;

    private String  bookState;

    private String bookType;

    private List<ChapterVO> chapters;
}
