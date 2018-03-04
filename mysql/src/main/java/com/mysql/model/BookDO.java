package com.mysql.model;

import lombok.Data;

import java.util.Date;

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
    private String bookCoding;
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
    /**
     * 作者编号（用户写的图书）
     */
    private Long authorId;
    /**
     * 图书的作者
     */
    private String authorName;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtUpdate;
}
