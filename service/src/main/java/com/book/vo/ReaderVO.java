package com.book.vo;

import lombok.Data;

import java.util.Date;
/**
 * Created by dingshuangkun on 2018/5/27.
 */
@Data
public class ReaderVO {
    /**
     * 读者Id
     */
    private Long id;
    /**
     * 账号
     */
    private String account;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 角色 0读 1写
     */
    private Integer role;
    /**
     * 积分
     */
    private Integer integration;
    /**
     * 状态
     */
    private Integer status;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtUpdate;
}
