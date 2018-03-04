package com.book.service;

import com.mysql.model.ReaderDO;

import java.util.List;

/**
 * Created by dingshuangkun on 2018/3/4.
 */
public interface ReaderService {
    /**
     * 根据Id 查询读者
     * @param id
     * @return
     */
    ReaderDO findReaderById(Long id);

    /**
     * 根据Id 集合查询读者
     * @param ids
     * @return
     */
    List<ReaderDO> findReaderById(List<Long> ids);

    /**
     * 判断读者是否存在
     * @param account
     * @param password
     * @return
     */
    Boolean readerIsExit(String account,String password);
}
