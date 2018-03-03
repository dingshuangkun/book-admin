package com.mysql.dao;

import com.mysql.model.BookDO;

import java.util.List;

/**
 * Created by dingshuangkun on 2018/2/7.
 */
public interface BookDAO {
    /**
     * 根据id 查询
     * @param id
     * @return
     */
    public BookDO findById(Long id);

    /**
     *
     * @param ids
     * @return
     */
    public List<BookDO> findById(List<Long> ids);

}
