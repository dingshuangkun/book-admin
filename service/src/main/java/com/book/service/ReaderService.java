package com.book.service;

import com.book.vo.ReaderVO;
import com.mysql.model.ReaderDO;
import com.mysql.query.QueryReader;

import java.util.List;

/**
 * Created by dingshuangkun on 2018/3/4.
 */
public interface ReaderService {

    /**
     * 查询读者
     * @param queryReader
     * @return
     */
    List<ReaderVO> queryReader(QueryReader queryReader);

    /**
     * 判断读者是否存在
     * @param account
     * @param password
     * @return
     */
    default Boolean readerIsExit(String account,String password){
        QueryReader queryReader = new QueryReader();
        queryReader.setPassword(password);
        queryReader.setAccount(account);
        List<ReaderVO> list = queryReader(queryReader);
        if(list!=null && list.size()>0){
            return true;
        }else {
            return false;
        }
    }
}
