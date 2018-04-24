package com.redis.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author  dingshuangkun
 * @ date on 2018/4/23.
 */
public class RedisCachePool {

    private JedisPool jedisPool;

    private Integer db;

    public RedisCachePool(Integer db,JedisPool jedisPool){
        this.jedisPool = jedisPool;
        this.db =db;
    }

    public Jedis getResouces(){
        Jedis jedis = null;
         if(jedisPool!=null){
             jedis = jedisPool.getResource();
             if(db>0){
                 jedis.select(db);
             }
         }
         return jedis;
    }

    public void returnResources(final Jedis jedis){
        if(jedis!=null){
            jedisPool.returnResource(jedis);
        }
    }
}
