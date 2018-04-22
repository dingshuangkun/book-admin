package util;

import redis.clients.jedis.Jedis;

/**
 * @author  dingshuangkun
 * @data on 2018/4/21.
 */
public class JedisUtil {

    private static JedisConnection jedisConnection =JedisConnetionFactory.getJedisConnection();
    private static Jedis jedis = jedisConnection.getJedis();
    public static String get(String key){
       return   jedis.get(key);
    }

    public static String set(String key , String  value){
       return    jedis.set(key,value);
    }

    public static String set(String key , String value,String nxxx){
       return   jedis.set(key,value,nxxx);
    }

    public static String set(String key,String value,String nxxx, String expx,long time){
        return jedis.set(key,value,nxxx,expx,time);
    }

    public static void coonClose(){
        jedisConnection.closeResouces();
    }
}
