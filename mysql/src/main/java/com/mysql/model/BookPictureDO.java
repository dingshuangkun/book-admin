package com.mysql.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by dingshuangkun on 2018/3/3.
 * 图书所对应的图片
 */
@Data
public class BookPictureDO {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 图书主键Id
     */
    private Long bookId;
    /**
     * 图书编码
     */
    private String bookCoding;
    /**
     * 图片地址
     */
    private String urls;
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
