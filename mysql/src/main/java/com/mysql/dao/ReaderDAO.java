package com.mysql.dao;

import com.mysql.model.ReaderDO;
import com.mysql.query.QueryReader;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by dingshuangkun on 2018/3/4.
 */
@Repository
public interface ReaderDAO {
    /**
     * 查找读者
     * @param queryReader
     * @return
     */
    List<ReaderDO> findReaders(QueryReader queryReader);
}
