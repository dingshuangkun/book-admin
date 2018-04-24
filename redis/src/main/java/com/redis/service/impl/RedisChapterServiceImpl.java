package com.redis.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.mysql.model.ChapterDO;
import com.redis.dao.RedisDAO;
import com.redis.service.RedisChapterService;
import com.redis.service.RedisNovelService;
import com.redis.util.RedisCacheManager;
import com.redis.util.RedisCachePool;
import com.redis.util.RedisDataBaseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * @author  dingshuangkun
 * @date on 2018/4/24.
 */
@Service
public class RedisChapterServiceImpl implements RedisChapterService {

    private static final String TABLE_NAME="novel_chapter";
    private static final String SPLIT_MARK=":";
    @Autowired
    private RedisCacheManager redisCacheManager;


    @Override
    public List<ChapterDO> queryByBookId(Long bookId) {
        RedisCachePool redisCachePool =   redisCacheManager.getRedisPoolMap().get(RedisDataBaseType.defaultType.toString());
        Jedis jedis = redisCachePool.getResouces();
        if(jedis!=null){
            String key=TABLE_NAME+SPLIT_MARK+bookId;
            String result =  RedisDAO.get(key,jedis);
            List<ChapterDO> chapterDOList = JSONArray.parseArray(result,ChapterDO.class);
            return chapterDOList;
        }
        return null;
    }

    @Override
    public ChapterDO queryById(Long id) {
        return null;
    }

    @Override
    public void addChapterList(List<ChapterDO> chapterDOList,Long bookId) {
         RedisCachePool redisCachePool =   redisCacheManager.getRedisPoolMap().get(RedisDataBaseType.defaultType.toString());
         Jedis jedis = redisCachePool.getResouces();
         if(jedis!=null){
             String key=TABLE_NAME+SPLIT_MARK+bookId;
             jedis.set(key, JSON.toJSON(chapterDOList).toString());
             redisCachePool.returnResources(jedis);
         }
    }


}
