package com.mysql.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by dingshuangkun on 2018/3/3.
 * 收藏/写了哪些书
 */
@Data
public class ReaderIntegrationDO {
    /**
     * 主键Id
     */
    private Long id;
    /**
     * 读者Id
     */
    private Long readerId;
    /**
     * 图书Id
     */
    private String booksId;
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
