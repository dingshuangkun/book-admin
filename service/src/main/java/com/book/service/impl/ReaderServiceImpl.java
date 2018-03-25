package com.book.service.impl;

import com.book.service.ReaderService;
import com.mysql.dao.ReaderDAO;
import com.mysql.model.ReaderDO;
import com.mysql.query.QueryReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dingshuangkun on 2018/3/4.
 */
@Service
public class ReaderServiceImpl implements ReaderService {

    @Autowired
    private ReaderDAO readerDAO;

    @Override
    public ReaderDO findReaderById(Long id) {
        List<Long> ids = new ArrayList<>();
        ids.add(id);
        List<ReaderDO> readerDOS = findReaderById(ids);
        if (readerDOS != null && readerDOS.size() > 0) {
            return readerDOS.get(0);
        }
        return null;
    }

    @Override
    public List<ReaderDO> findReaderById(List<Long> ids) {
        QueryReader queryReader = new QueryReader();
        queryReader.setIds(ids);
        return readerDAO.findReaders(queryReader);
    }

    @Override
    public Boolean readerIsExit(String account, String password) {
        QueryReader queryReader = new QueryReader();
        queryReader.setAccount(account);
        queryReader.setPassword(password);
        List<ReaderDO> readerDOS = readerDAO.findReaders(queryReader);
        if (readerDOS != null && readerDOS.size() > 0) {
            return true;
        }
        return false;
    }
}
