package com.book.controller;

import com.book.service.BookService;
import com.mysql.model.BookDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author  dingshuangkun
 * 2018/2/9.
 */
@Controller
@RequestMapping("/test")
public class BookController {

    @Autowired
    private BookService bookService;

    @RequestMapping("/test")
    @ResponseBody
    public BookDO test(@RequestParam("id") Long id){

        return bookService.findById(id);
    }
}
