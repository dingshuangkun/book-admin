package com.mysql.query;

import lombok.Data;

/**
 * @author  dingshuangkun
 * @Date on 2018/4/15.
 */
@Data
public class QueryNovel extends Pagination {
    /**
     * id
     */
    private Long id;
    /**
     * 作者
     */
    private String author;
    /**
     * 状态
     */
    private Integer bookState;
    /**
     * 书名
     */
    private String bookName;
    /**
     * 是否查询章节
     */
    private String  queryChapters;
}
