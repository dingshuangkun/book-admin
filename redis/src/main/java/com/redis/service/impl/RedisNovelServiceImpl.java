package com.redis.service.impl;

import com.alibaba.fastjson.JSON;
import com.mysql.model.NovelDO;
import com.redis.dao.RedisDAO;
import com.redis.service.RedisNovelService;
import com.redis.util.RedisCacheManager;
import com.redis.util.RedisCachePool;
import com.redis.util.RedisDataBaseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author dingshuangkun
 * @date on 2018/4/24.
 */
@Service
public class RedisNovelServiceImpl implements RedisNovelService {

    /**
     * 数据库表名
     */
    private static final String TABLE_NAME = "novel";

    /**
     * 主键
     */
    private static final String PRIMARY_KEY = "bookId";

    private static final String SPLIT_MARK = ":";

    @Autowired
    private RedisCacheManager redisCacheManager;

    private RedisCachePool redisCachePool;

    private Jedis jedis;

    /**
     * 初始化链接
     */
    @PostConstruct
    public void init() {
        /**
         * @see com.redis.service.impl.RedisChapterServiceImpl
         * 在这里也有类似的代码，可以实现一个jedis util类来实现类似的功能
         */
        redisCachePool = redisCacheManager.getRedisPoolMap().get(RedisDataBaseType.defaultType.toString());
        jedis = redisCachePool.getResouces();
    }

    @Override
    public List<NovelDO> queryAll() {
        //这一行的意义是什么？
//        Jedis jedis = redisCachePool.getResouces();
        RedisDAO rd = new RedisDAO(jedis);
        Set<String> bookIdSet = rd.keys("novel:bookId:*");
        List<String> rs = RedisDAO.getListString(bookIdSet, jedis);
        List<NovelDO> list = new ArrayList<>();

        //lamb表达式可以更简略成这样
        rs.forEach(n -> list.add(JSON.parseObject(n, NovelDO.class)));

        return list;
    }

    @Override
    public NovelDO queryById(Long id) {
//        RedisDAO rd = new RedisDAO(jedis);
        String result = RedisDAO.get(getKeyWithPrimaryKey(id), jedis);

        return JSON.parseObject(result, NovelDO.class);
    }

    /**
     * 获取redis key
     * 结合表名与主键名
     * @param id
     * @return
     */
    private String getKeyWithPrimaryKey(Long id){
        /**
         * 我自己喜欢用这种做法对类似的方法进行抽象
         * 一个是可以对参数进行校验
         * 一个是可以让代码语义化
         */
        Assert.notNull(id, "id不能为空");

        return TABLE_NAME + SPLIT_MARK + PRIMARY_KEY + SPLIT_MARK + id;
    }


    @Override
    public NovelDO queryByTitle(String title) {
        return null;
    }

    @Override
    public NovelDO queryByAuthor(String author) {
        return null;
    }

    @Override
    public void addNovelDO(NovelDO novelDO) {
        //可以看一下原先的代码，有两个问题就是没有close 事务，这里用了这种做法，叫做try-with-resource，可以自动帮助你完成close
        try (Transaction transaction = jedis.multi()){
            RedisDAO rd = new RedisDAO(transaction);

            rd.set(getKeyWithPrimaryKey(novelDO.getId()), JSON.toJSON(novelDO).toString());
            transaction.exec();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
