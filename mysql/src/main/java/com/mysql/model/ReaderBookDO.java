package com.mysql.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by dingshuangkun on 2018/3/3.
 */
@Data
public class ReaderBookDO {
    /**
     * 主键id
     */
    private Long id;
    /**
     * 图书Id
     */
    private Long bookId;
    /**
     * 读者/写者Id
     */
    private Long readerId;
    /**
     * 读/写的位置
     */
    private Long position;
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
