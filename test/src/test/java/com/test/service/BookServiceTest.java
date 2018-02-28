package com.test.service;

import com.book.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by dingshuangkun on 2018/2/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext.xml")
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    public void test(){
        System.out.println(bookService.findById(1L));
    }
}
