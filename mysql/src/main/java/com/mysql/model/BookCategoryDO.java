package com.mysql.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by dingshuangkun on 2018/3/3.
 * 图书类目
 */
@Data
public class BookCategoryDO {
    /**
     * 主键
     */
    private Long id;
    /**
     * 类目编码
     */
    private String code;
    /**
     *该类目下所包含的图书编码
     */
    private String bookCoding;
    /**
     * 类目的名字
     */
    private String name;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 标签
     */
    private String tag;
    /**
     * 修改时间
     */
    private Date gmtUpdate;

}
