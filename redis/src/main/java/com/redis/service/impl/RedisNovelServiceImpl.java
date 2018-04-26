package com.redis.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.mysql.model.NovelDO;
import com.redis.dao.RedisDAO;
import com.redis.service.RedisNovelService;
import com.redis.util.RedisCacheManager;
import com.redis.util.RedisCachePool;
import com.redis.util.RedisDataBaseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    // 数据库表名
    private static final String TABLE_NAME="novel";
    //主键
    private static final String PK="bookId";

    private static final String SPLIT_MARK=":";
    @Autowired
    private  RedisCacheManager redisCacheManager;

    private   RedisCachePool redisCachePool;

    private  Jedis jedis;

    /**
     * 初始化链接
     */
    @PostConstruct
    public void init(){
        redisCachePool = redisCacheManager.getRedisPoolMap().get(RedisDataBaseType.defaultType.toString());
        jedis = redisCachePool.getResouces();
    }
    @Override
    public List<NovelDO> queryAll() {

        Jedis jedis = redisCachePool.getResouces();
        RedisDAO rd = new RedisDAO(jedis);
        Set<String> bookId = rd.keys("novel:bookId:*");
        List<String> rs = RedisDAO.getListString(bookId,jedis);
        List<NovelDO> list = new ArrayList<>();
        rs.forEach(n->{
           NovelDO novelDO =  JSON.parseObject(n,NovelDO.class);
           list.add(novelDO);
        });
        return list;
    }

    @Override
    public NovelDO queryById(Long id) {
        RedisDAO rd = new RedisDAO(jedis);
        String resut = RedisDAO.get(TABLE_NAME+SPLIT_MARK+PK+SPLIT_MARK+id,jedis);
        NovelDO novelDO =  JSON.parseObject(resut,NovelDO.class);
        return novelDO;
    }


    @Override
    public NovelDO queryByTitle(String title) {
        return null;
    }

    @Override
    public NovelDO queryByauthor(String author) {
        return null;
    }

    @Override
    public void addNovelDO(NovelDO novelDO){
       Transaction   transaction = jedis.multi();
        RedisDAO rd = new RedisDAO(transaction);
        try {
          rd.set(TABLE_NAME+SPLIT_MARK+PK+SPLIT_MARK+novelDO.getId(), JSON.toJSON(novelDO).toString());
          transaction.exec();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            redisCachePool.returnResources(jedis);
        }

    }
}
