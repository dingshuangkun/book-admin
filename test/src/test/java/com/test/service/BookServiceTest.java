package com.test.service;

import com.book.service.BookService;
import com.mysql.model.BookDO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dingshuangkun on 2018/2/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext.xml")
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    public void testSelect(){

        System.out.println(bookService.findById(3L));
    }

    @Test
    public void testInsert(){
        List<BookDO> list = new ArrayList<>();
        BookDO bookDO = new BookDO();
        bookDO.setBookCoding("7568926");
        bookDO.setName("明朝那些事一");
        bookDO.setBookIndex("朱元璋");
        bookDO.setAuthorName("当年明月");
        bookDO.setAuthorId(0L);
        list.add(bookDO);
        bookService.batchInsertBookDO(list);
    }
}
