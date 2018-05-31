package com.book.service.impl;

import com.book.service.ReaderService;
import com.book.vo.ReaderVO;
import com.mysql.dao.ReaderDAO;
import com.mysql.model.ReaderDO;
import com.mysql.query.QueryReader;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dingshuangkun
 * @date on 2018/3/4.
 */
@Service
public class ReaderServiceImpl implements ReaderService {

    @Autowired
    private ReaderDAO readerDAO;

    @Override
    public List<ReaderVO> queryReader(QueryReader queryReader) {
        List<ReaderDO> rds = readerDAO.findReaders(queryReader);
        if(rds != null && rds.size() > 0) {
            List<ReaderVO> rvs = new ArrayList<>();
            rds.forEach(n -> {
                ReaderVO rv = new ReaderVO();
                BeanUtils.copyProperties(n, rv);
                rvs.add(rv);
            });
            return rvs;
        }
        return null;
    }
}
