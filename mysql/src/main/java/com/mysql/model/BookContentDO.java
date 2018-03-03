package com.mysql.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by dingshuangkun on 2018/3/3.
 * 图书的内容
 */
@Data
public class BookContentDO {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 图书编码
     */
    private String bookCoding;
    /**
     * 图书的内容
     */
    private String content;
    /**
     * 图书大小
     */
    private Long size;
    /**
     * 标签
     */
    private String tag;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtUpdate;
}
