package com.mysql.query;

import lombok.Data;

/**
 * Created by dingshuangkun on 2018/4/15.
 */
@Data
public class QueryChapter extends Pagination{
    /**
     * 主键id
     */
    private Long id;
    /**
     * 根据小说的章节查询
     */
    private Long bookId;
    /**
     * 章节标题
     */
    private String title;

}
