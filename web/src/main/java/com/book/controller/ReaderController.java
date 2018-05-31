package com.book.controller;
import com.book.service.ReaderService;
import com.book.vo.ReaderVO;
import com.mysql.query.QueryReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * @author  dingshuangkun
 * @date on 2018/5/27.
 */
@RequestMapping("/reader/")
@Controller
public class ReaderController {
    @Autowired
    private ReaderService readerService;
    @RequestMapping("query")
    @ResponseBody
    @CrossOrigin(origins = "*" ,maxAge = 360)
    public List<ReaderVO> query(QueryReader queryReader){
        List<ReaderVO> list = readerService.queryReader(queryReader);
        return list;
    }

    @RequestMapping("readerIsExit")
    @ResponseBody
    @CrossOrigin(origins = "*" ,maxAge = 360)
    public Boolean userIsExit(String account, String password){
        return readerService.readerIsExit(account,password);
    }
}
