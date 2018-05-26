package com.redis.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.mysql.model.ChapterDO;
import com.redis.dao.RedisDAO;
import com.redis.service.RedisChapterService;
import com.redis.util.RedisCacheManager;
import com.redis.util.RedisCachePool;
import com.redis.util.RedisDataBaseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import redis.clients.jedis.Jedis;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * @author dingshuangkun
 * @date on 2018/4/24.
 */
@Service
public class RedisChapterServiceImpl implements RedisChapterService {

    /**
     * 表名
     * 用于组合成redis key
     */
    private static final String TABLE_NAME = "novel_chapter";

    /**
     * 分隔符
     * 用于组合成redis key
     */
    private static final String SPLIT_MARK = ":";

    @Autowired
    private RedisCacheManager redisCacheManager;

    private RedisCachePool redisCachePool;

    private Jedis jedis;

    private Boolean is_ini = false;

    @PostConstruct
    public void init() {
        /**
         * 这种做法是线程不安全的，在这里也没有必要
         * 但是考虑到下面的代码中要出现了if(jedis == null)的这种做法（你可以看一下你的git history）
         * 所以这里这样做，我平常遇到需要这样初始化的类的时候通常用这种做法，会用双重锁实现单例
         */
        if (!is_ini) {
            ini();
        }
    }

    /**
     * 类初始化
     */
    private void ini() {
        redisCachePool = redisCacheManager.getRedisPoolMap().get(RedisDataBaseType.defaultType.toString());
        jedis = redisCachePool.getResouces();
        is_ini = true;
    }

    @Override
    public List<ChapterDO> queryByBookId(Long bookId) {
//        if(jedis!=null){
//            String key=TABLE_NAME+SPLIT_MARK+bookId;
//            String result =  RedisDAO.get(key,jedis);
//            List<ChapterDO> chapterDOList = JSONArray.parseArray(result,ChapterDO.class);
//            return chapterDOList;
//        }
//        return null;

        if (!is_ini) {
            ini();
        }

        String result = RedisDAO.get(getRedisKey(bookId), jedis);

        return JSONArray.parseArray(result, ChapterDO.class);
    }

    /**
     * 获取redis key
     *
     * @param bookId
     * @return
     */
    private String getRedisKey(Long bookId) {
        Assert.notNull(bookId, "bookId不能为空");

        return TABLE_NAME + SPLIT_MARK + bookId;
    }

    @Override
    public ChapterDO queryById(Long id) {
        return null;
    }

    @Override
    public void addChapterList(List<ChapterDO> chapterDOList, Long bookId) {
        if (!is_ini) {
            ini();
        }

        jedis.set(getRedisKey(bookId), JSON.toJSON(chapterDOList).toString());
        redisCachePool.returnResources(jedis);
    }
}
