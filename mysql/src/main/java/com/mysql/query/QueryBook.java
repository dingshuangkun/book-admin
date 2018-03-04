package com.mysql.query;

import lombok.Data;

import java.util.List;

/**
 * @author  dingshuangkun
 * on 2018/3/4.
 * 查找book 表的参数
 */
@Data
public class QueryBook {
    /**
     * 图书的id
     */
    private Long id;
    /**
     * 图书id的集合
     */
    private List<Long> ids;
    /**
     * 图书编码
     */
    private String bookCoding;
    /**
     * 图书编码集合
     */
    private List<String> bookCodings;
    /**
     * 作者
     */
    private String author;
    /**
     * 作者编号
     */
    private Long authorId;
}
