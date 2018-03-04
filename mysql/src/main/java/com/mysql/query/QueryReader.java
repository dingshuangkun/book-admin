package com.mysql.query;

import lombok.Data;

import java.util.List;

/**
 * @author  dingshuangkun
 * on 2018/3/4.
 */
@Data
public class QueryReader {
    /**
     * 读者Id
     */
    private Long id;
    /**
     * id 集合
     */
    private List<Long> ids;
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
}
