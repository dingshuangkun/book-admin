package com.mysql.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by dingshuangkun on 2018/3/3.
 */
@Data
public class BookFavoriteDO {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 图书编码
     */
    private String bookCoding;
    /**
     * 读者
     */
    private Long readerId;
    /**
     * 阅读次数
     */
    private int views;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtUpdate;
}
