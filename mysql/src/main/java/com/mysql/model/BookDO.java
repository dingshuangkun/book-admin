package com.mysql.model;

import lombok.Data;

/**
 * @author dingshuangkun on 2018/2/7.
 */

@Data
public class BookDO {

    /**
     * 主键 Id
     */
    private Long id;

    /**
     * 图书code
     */
    private String code;

    /**
     * 书名
     */
    private String name;

    /**
     * 标签
     */
    private String tag;

    /**
     * 索引
     */
    private String bookIndex;
}
