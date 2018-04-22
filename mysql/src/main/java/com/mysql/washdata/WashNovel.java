package com.mysql.washdata;

import com.mysql.dao.NovelDAO;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by dingshuangkun on 2018/4/19.
 */
public class WashNovel {

    public static void main(String[] arges) throws IOException{

        String resource = "org/mybatis/example/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession session = sqlSessionFactory.openSession();

        try {
            NovelDAO novelDAO = session.getMapper(NovelDAO.class);
           // novelDAO.selectByPrimaryKey()
        } finally {
            session.close();
        }
    }
}
